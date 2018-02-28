package com.fanhunoo.clover.controller;

import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.IUserService;
import com.fanhunoo.clover.util.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public String queryUsers(HttpServletRequest request, HttpServletResponse response) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String roleId = userDetails.getRoleId();
        List<User> users;
        //如果是超管或老板角色，则可查询所有角色，否则只能查询当前机构
        if(StringUtils.equals(roleId, Constant.ROLE_SUPER_ADMIN) || StringUtils.equals(roleId, Constant.ROLE_BOSS)){
            users = userService.findUsersBy(null);
        }else {
            users = userService.findUsersBy(userDetails.getOrgId());
        }
        request.setAttribute("users",users);
        return "system/user/user";
    }

}
