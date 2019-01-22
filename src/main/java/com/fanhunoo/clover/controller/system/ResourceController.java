package com.fanhunoo.clover.controller.system;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.ResourcesService;
import com.fanhunoo.clover.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统管理--资源管理
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController {

    @Autowired
    private ResourcesService resourcesService;

    /**
     * 页面
     */
    @GetMapping("/")
    public String resource() {
        return "system/resource/index";
    }

    /**
     * 资源列表
     */
    @GetMapping("/list")
    @ResponseBody
    public MyPage list() {
        //按角色权限去查
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Resources> roles;
        if(Constant.ROLE_SUPER_ADMIN.equals(userDetails.getRoleId())){
            roles = resourcesService.queryAll();
        }else{
            roles = resourcesService.selectByRoleId(userDetails.getRoleId());
        }
        return new MyPage(Constant.PAGE_SUCCESS,"",Long.valueOf(roles.size()),roles);
    }

    /**
     * 父级资源列表
     */
    @GetMapping("/parents")
    @ResponseBody
    public Result parents(@RequestParam Integer type) {
        List<Resources> parents = null;
        if(type==2 || type==3 || type==9){//2级菜单、3级菜单、按钮
            Map<String,Object> param = new HashMap<>();
            param.put("type",type == 9? 3:type-1);
            parents = resourcesService.queryByCondition(param);
        }
        return new Result(parents);
    }

    /**
     * 保存资源
     */
    @PostMapping("/")
    @ResponseBody
    public Result save(@RequestBody Resources resources) {
        Result result = new Result();
        try {
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            resources.setCreatePer(userDetails.getRealName());
            resources.setUpdatePer(userDetails.getRealName());
            resourcesService.saveResource(resources);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("保存资源成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("保存资源出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 更新资源
     */
    @PutMapping("/")
    @ResponseBody
    public Result edit(@RequestBody Resources resources) {
        Result result = new Result();
        try {
            if(resources==null || StringUtils.isEmpty(resources.getId())){
                throw new RuntimeException("资源id为空！");
            }
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            resources.setUpdatePer(userDetails.getRealName());
            resourcesService.updateResource(resources);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("更新资源成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("更新资源出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 删除资源
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id){
        Result result = new Result();
        try {
            if(StringUtils.isEmpty(id)){
                throw new RuntimeException("资源id为空！");
            }
            resourcesService.deleteResource(id);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("删除资源成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("删除资源出错！"+e.getMessage());
        }
        return result;
    }
}
