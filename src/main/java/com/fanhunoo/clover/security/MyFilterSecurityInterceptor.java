package com.fanhunoo.clover.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;


/**
 * 自定义权限管理过滤器
 * 使访问程序时，检查当前用户是否拥有当前url的权限
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Resource
    private MySecurityMetadataSource securityMetadataSource;
    @Resource
    private MyAccessDecisionManager accessDecisionManager;

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    @PostConstruct
    public void init() throws Exception{
        super.setAccessDecisionManager(accessDecisionManager);
        super.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    /**
     * object为FilterInvocation对象
     * super.beforeInvocation(fi);源码
     * 1.获取请求资源的权限
     * 执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);
     * 2.是否拥有权限
     * this.accessDecisionManager.decide(authenticated, object, attributes);
     * fi里面有一个被拦截的url
     * 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     */
    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch(Exception e) {
           e.printStackTrace();
            //没有权限
            throw new AccessDeniedException(" 没有权限访问或未重新登录！ ");
        } finally {
            super.afterInvocation(token, null);
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public Class<?> getSecureObjectClass() {
        //下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误
        return FilterInvocation.class;
    }
}
