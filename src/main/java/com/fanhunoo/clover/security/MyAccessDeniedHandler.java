package com.fanhunoo.clover.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        request.setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);
        response.setStatus(403);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/error/403");
        dispatcher.forward(request, response);
    }

}
