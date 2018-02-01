package com.fanhunoo.clover.security;

import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.service.IResourcesService;
import com.fanhunoo.clover.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义认证：
 * 就是在登录时的认证操作交给Spring Security.在此处需要提供给当前登录用户所拥有的权限。
 * 即根据用户名查询上面t_resources表中的resKey 拼凑成”ROLE_XXX“ 这样形式的字符串所组成的list ，交给spirngSecurity。
 */
//@Component("myUserDetailService")
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Autowired
    private IResourcesService resourcesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        if(user ==null)
            throw new UsernameNotFoundException(username+" not exist!");
        Set<GrantedAuthority> authSet = new HashSet<>();
        List<Resources> list = resourcesService.loadMenu(username);
        for (Resources r : list) {
            authSet.add(new SimpleGrantedAuthority("ROLE_" +r.getKey()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassWord(),
                user.getStatus() == 1,
                true,
                true,
                true,
                authSet);
    }
}
