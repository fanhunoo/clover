package com.fanhunoo.clover.security;

import com.fanhunoo.clover.util.CommonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationProcessingFilterEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public MyAuthenticationProcessingFilterEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    /**
     * @author ligh4 2015年4月1日下午4:38:04
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException,
            ServletException {

        /**
         *  判断是跳转login页面还是弹出login弹窗
         *  目前前端用的iframe不是ajax，只在请求home首页会跳转登录页，其他都是弹窗
         */
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        boolean flag = "/home".equals(httpRequest.getServletPath());
        if (flag) {
            //login_dia.ftl
            request.getRequestDispatcher("/dialogLogin").forward(request, response);
//            this.redirectStrategy.sendRedirect(request, response, redirectUrl);
        } else {
            super.commence(request, response, authException);
        }
    }
}
