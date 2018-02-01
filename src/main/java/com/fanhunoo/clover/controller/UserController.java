package com.fanhunoo.clover.controller;

import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "User控制器")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "根据用户id查询用户信息", httpMethod = "GET", produces = "application/json")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping("/user")
    public String selectUser(
            @ApiParam(name = "id", required = true, value = "用户Id")
            @RequestParam(value = "id") Integer id,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User user = this.userService.selectUser(id);
        request.setAttribute("user",user);
        return "user";
    }

    @RequestMapping("/rrr")
    public String rrr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Integer userId = Integer.valueOf(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
       // response.getWriter().write(mapper.writeValueAsString(user));
        request.setAttribute("user",user);
        return "rrr";
    }

    @RequestMapping("/user1")
    public String selectUser1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Integer userId = Integer.valueOf(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
       // response.getWriter().write(mapper.writeValueAsString(user));
        request.setAttribute("user",user);
        return "system/user/user";
    }

}
