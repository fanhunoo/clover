package com.fanhunoo.clover.controller.system;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.Dictionary;
import com.fanhunoo.clover.entity.Role;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.DictionaryService;
import com.fanhunoo.clover.service.RoleService;
import com.fanhunoo.clover.service.UserService;
import com.fanhunoo.clover.util.CommonUtils;
import com.fanhunoo.clover.util.Constant;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统管理--用户管理
 */
@Controller
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 页面
     */
    @GetMapping("/")
    public String user(HttpServletRequest request) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> roles = roleService.selectByRoleIdLimitRank(userDetails.getRoleId());
        List<Dictionary> orgs = dictionaryService.selectByTitle(Constant.DICTIONARY_TITLE_ORG);
        request.setAttribute("roles",roles);
        request.setAttribute("orgs",orgs);
        return "system/user/user";
    }

    /**
     * 用户列表
     */
    @ResponseBody
    @GetMapping("/list")
    public MyPage list(HttpServletRequest request) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String roleId = userDetails.getRoleId();
        PageHelper.startPage(request);
        List<User> users;
        //如果是超管或老板角色，则可查询所有角色，否则只能查询当前机构
        if(CommonUtils.checkSpecialPermission(roleId)){
            users = userService.findUsersBy("selectAll",null);
        }else {
            users = userService.findUsersBy(null,userDetails.getOrgId());
        }
        return new MyPage(users);
    }

    /**
     * 保存用户
     */
    @PostMapping("/")
    @ResponseBody
    public Result save(@RequestBody User user) {
        Result result = new Result();
        try {
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user.setCreatePer(userDetails.getRealName());
            user.setUpdatePer(userDetails.getRealName());
            user.setStatus(user.getStatus()==null?0:1);
            user.setPassWord(CommonUtils.encodeDefaultPassword());
            userService.saveUser(user);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("保存用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("保存用户出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 更新用户
     */
    @PutMapping("/")
    @ResponseBody
    public Result edit(@RequestBody User user) {
        Result result = new Result();
        try {
            if(user==null || null==user.getId()){
                throw new RuntimeException("用户id为空！");
            }
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user.setUpdatePer(userDetails.getRealName());
            user.setStatus(user.getStatus()==null?0:1);
            user.setPassWord(null);//不更新密码;密码在单独的修改个人资料中
            userService.updateUser(user);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("更新用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("更新用户出错！"+e.getMessage());
        }
        return result;
    }


    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id){
        Result result = new Result();
        try {
            if(StringUtils.isEmpty(id)){
                throw new RuntimeException("用户id为空！");
            }
            userService.deleteUserById(id);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("删除用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("删除用户出错！"+e.getMessage());
        }
        return result;
    }


}
