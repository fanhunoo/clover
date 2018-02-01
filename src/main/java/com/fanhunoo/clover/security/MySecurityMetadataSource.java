package com.fanhunoo.clover.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.fanhunoo.clover.dao.IResourcesDao;
import com.fanhunoo.clover.entity.Resources;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

/**
 * 权限配置资源管理器
 * 加载资源与权限的对应关系  http://blog.csdn.net/poorCoder_/article/details/70231779
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private IResourcesDao resourcesDao;

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
     *
     * //加载所有资源与权限的关系
     */
    @PostConstruct
    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<>();
            List<Resources> list = resourcesDao.queryAll();
            for (Resources resources : list) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<>();
                // 通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
                ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resources.getKey());
                configAttributes.add(configAttribute);
                resourceMap.put(resources.getUrl(), configAttributes);
            }
        }
    }
    //返回所请求资源所需要的权限
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //  System.out.println("requestUrl is " + requestUrl);
        if(resourceMap == null) {
            loadResourceDefine();
        }
        //System.err.println("resourceMap.get(requestUrl); "+resourceMap.get(requestUrl));
        if(requestUrl.contains("?")){
            requestUrl = requestUrl.substring(0,requestUrl.indexOf("?"));
        }
        return resourceMap.get(requestUrl);
//
//        if(map ==null) loadResourceDefine();
//        //object 中包含用户请求的request 信息
//        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
//        AntPathRequestMatcher matcher;
//        String resUrl;
//        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
//            resUrl = iter.next();
//            matcher = new AntPathRequestMatcher(resUrl);
//            if(matcher.matches(request)) {
//                return map.get(resUrl);
//            }
//        }
//        return null;
    }


}