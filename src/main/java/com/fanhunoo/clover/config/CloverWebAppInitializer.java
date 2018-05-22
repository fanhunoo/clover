package com.fanhunoo.clover.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
/**
 * 配置DispatcherServlet
 */
public class CloverWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class,DBConfig.class,CacheConfig.class};//定义ContextLoaderListener应用上下文
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};//定义DispatcherServlet应用上下文
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};//将DispatcherServlet映射到"/"
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setLoadOnStartup(1);
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/clover/uploads"));//配置Servlet 3.0对multipart的支持
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { characterEncodingFilter() };
    }


}
