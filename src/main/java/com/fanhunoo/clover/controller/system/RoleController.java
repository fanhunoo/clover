package com.fanhunoo.clover.controller.system;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.entity.Dictionary;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.RoleService;
import com.fanhunoo.clover.service.UserService;
import com.fanhunoo.clover.util.Constant;
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
import java.util.Set;

/**
 * 系统管理--角色管理
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 页面
     */
    @GetMapping("/")
    public String user() {
        return "system/role/list";
    }

    /**
     * 角色列表
     */
    @ResponseBody
    @GetMapping("/list")
    public MyPage list(HttpServletRequest request) {
        PageHelper.startPage(request);
        List<Dictionary> roles = roleService.findAllRole();
        return new MyPage(roles);
    }
}
