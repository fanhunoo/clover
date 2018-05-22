package com.fanhunoo.clover.security;

import com.fanhunoo.clover.dao.IResourcesDao;
import com.fanhunoo.clover.entity.Resources;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * 安全源数据源
 * 权限配置资源管理器
 * 加载资源与权限的对应关系
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private IResourcesDao resourcesDao;

    //todo: 更新权限和资源表时要刷新此map
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    /**
     * @PostConstruct是Java EE 5引入的注解，
     * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
     * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
     * 加载所有资源与权限的关系
     */
    @PostConstruct
    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<>();
            List<Resources> list = resourcesDao.queryAll();
            for (Resources resources : list) {
                //用Collection就是为了应对单个请求需要多个权限的情况
                Collection<ConfigAttribute> configAttributes = new ArrayList<>();
                //本系统业务上单个请求就对应单个权限，所以直接用资源id来表示具体的权限,注意：必须"ROLE_"开头
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resources.getId());
                configAttributes.add(configAttribute);
                resourceMap.put(resources.getUrl(), configAttributes);
            }
        }
    }

    //返回所请求资源所需要的权限
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String ajaxFlag = ((FilterInvocation) object).getHttpRequest().getHeader("X-Requested-With");
        //return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        if(resourceMap == null) {
            loadResourceDefine();
        }
        if(requestUrl.contains("?")){
            requestUrl = requestUrl.substring(0,requestUrl.indexOf("?"));
        }
        return resourceMap.get(requestUrl);
    }

}