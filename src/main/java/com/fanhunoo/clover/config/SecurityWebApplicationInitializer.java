package com.fanhunoo.clover.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 配置Delegating-FilterProxy
 * AbstractSecurityWebApplicationInitializer实现了 WebApplicationInitializer，
 * 因此Spring会发现它，并用它 在Web容器中注册DelegatingFilterProxy
 * 等价于xml配置
 * <filter>
 *      <filter-name>springSecurityFilterChain</filter-name>
 *      <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 *  </filter>
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
