package com.fanhunoo.clover.controller;

import com.fanhunoo.clover.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录相关
 */
@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);//日志

    @Autowired
    UserService userService;


    /**
     * t跳转登陆页面
     * @param request
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout){
        String errorMsg = "";
        String logoutMsg = "";
        if (error != null) {
            Object exception = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            if(exception instanceof org.springframework.security.core.userdetails.UsernameNotFoundException){
                errorMsg = "用户名或密码错误";
            }else if(exception instanceof org.springframework.security.authentication.DisabledException){
                errorMsg = "用户未启用";
            }else if(exception instanceof org.springframework.security.authentication.BadCredentialsException){
                errorMsg = "用户名或密码错误";
            }
            logger.info("------:"+errorMsg);
        }
        if (logout != null) {
            logoutMsg = "您已经成功退出";
        }
        request.setAttribute("errorMsg",errorMsg);
        request.setAttribute("logoutMsg",logoutMsg);
        return "login/login";
    }

    /**
     * 跳转登录弹窗
     * @return
     */
    @GetMapping("/dialogLogin")
    public String dialogLogin(){
        return "login/login_dia";
    }

}
