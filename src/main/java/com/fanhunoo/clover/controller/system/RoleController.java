package com.fanhunoo.clover.controller.system;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.entity.Role;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.ResourcesService;
import com.fanhunoo.clover.service.RoleService;
import com.fanhunoo.clover.util.CommonUtils;
import com.fanhunoo.clover.util.Constant;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 系统管理--角色管理
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourcesService resourcesService;

    /**
     * 页面
     */
    @GetMapping("/")
    public String role() {
        return "system/role/list";
    }

    /**
     * 角色列表
     */
    @ResponseBody
    @GetMapping("/list")
    public MyPage list(HttpServletRequest request) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageHelper.startPage(request);
//        List<Role> roles = roleService.selectAll();
        List<Role> roles = roleService.selectByRoleIdLimitRank(userDetails.getRoleId());
        return new MyPage(roles);
    }


    /**
     * 保存角色
     */
    @PostMapping("/")
    @ResponseBody
    public Result save(@RequestBody Role role) {
        Result result = new Result();
        try {
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            role.setCreatePer(userDetails.getRealName());
            role.setUpdatePer(userDetails.getRealName());
            role.setStatus(role.getStatus()==null?0:1);
            roleService.saveRole(role);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("保存角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("保存角色出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 更新角色
     */
    @PutMapping("/")
    @ResponseBody
    public Result edit(@RequestBody Role role) {
        Result result = new Result();
        try {
            if(role==null || null==role.getId()){
                throw new RuntimeException("角色id为空！");
            }
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            role.setUpdatePer(userDetails.getRealName());
            role.setStatus(role.getStatus()==null?0:1);
            roleService.updateRole(role);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("更新角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("更新角色出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id){
        Result result = new Result();
        try {
            if(StringUtils.isEmpty(id)){
                throw new RuntimeException("角色id为空！");
            }
            roleService.deleteRoleById(id);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("删除角色成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("删除角色出错！"+e.getMessage());
        }
        return result;
    }

    /**
     * 获取权限
     */
    @GetMapping("/permission/{id}")
    @ResponseBody
    public Result permission(@PathVariable String id){
        Result result = new Result();
        try {
            if(StringUtils.isEmpty(id)){
                throw new RuntimeException("角色id为空！");
            }
            //已经拥有的权限
            List<Resources> hadResources = resourcesService.selectByRoleId(id);
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //可选择的权限（当前登录的用户）
            List<Resources> resources = resourcesService.selectByRoleId(userDetails.getRoleId());
            for(Resources resource : resources){
                Iterator<Resources> itHad = hadResources.iterator();
                while (itHad.hasNext()){
                    if(StringUtils.equals(resource.getId(),itHad.next().getId())){
                        resource.setChecked(true);
                        itHad.remove();
                        break;
                    }
                }
            }
            result.setData(CommonUtils.resourcesToTree(resources));//转换为树
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("获取权限成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("获取权限出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 修改权限
     */
    @PostMapping("/permission")
    @ResponseBody
    public Result updatePermission(HttpServletRequest request){
        Result result = new Result();
        try {
            String roleId = request.getParameter("roleId");
            String data = request.getParameter("data");
            if(StringUtils.isEmpty(roleId)){
                throw new RuntimeException("角色id为空！");
            }
            List<Map<String,String>> permissions = CommonUtils.getObjectMapper().readValue(data,List.class);
            if(permissions != null && permissions.size()>0){
                for(Map<String,String> map : permissions){
                    map.put("roleId",roleId);
                }
            }
            resourcesService.updatePermission(roleId,permissions);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("修改权限成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("修改权限出错！"+e.getMessage());
        }
        return result;
    }
}
