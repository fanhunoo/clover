package com.fanhunoo.clover.controller.system;

import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.UserService;
import com.fanhunoo.clover.util.Constant;
import com.fanhunoo.clover.base.MyPage;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统管理--用户管理
 */
@Controller
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 页面
     */
    @GetMapping("/")
    public String user(HttpServletRequest request, HttpServletResponse response) {
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String roleId = userDetails.getRoleId();
//        List<User> users;
//        //如果是超管或老板角色，则可查询所有角色，否则只能查询当前机构
//        if(StringUtils.equals(roleId, Constant.ROLE_SUPER_ADMIN) || StringUtils.equals(roleId, Constant.ROLE_BOSS)){
//            users = userService.findUsersBy(null);
//        }else {
//            users = userService.findUsersBy(userDetails.getOrgId());
//        }
//        request.setAttribute("users",users);
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
        if(StringUtils.equals(roleId, Constant.ROLE_SUPER_ADMIN) || StringUtils.equals(roleId, Constant.ROLE_BOSS)){
            users = userService.findUsersBy("selectAll",null);
        }else {
            users = userService.findUsersBy(null,userDetails.getOrgId());
        }
        return new MyPage(users);
    }
}
