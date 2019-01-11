package com.fanhunoo.clover.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class MyUserDetails extends User {
    //真实姓名
    private String realName;
    //手机号
    private String phone;
    //所在店id(0-总;1-1号店)
    private Integer orgId;
    //最后登陆时间
    private LocalDateTime lastLoginTime;
    //角色id
    private String roleId;

    MyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked,
                  Collection<? extends GrantedAuthority> authorities
            , String realName, String phone, Integer orgId, LocalDateTime lastLoginTime, String roleId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.realName = realName;
        this.phone = phone;
        this.orgId = orgId;
        this.lastLoginTime = lastLoginTime;
        this.roleId = roleId;
    }
}
