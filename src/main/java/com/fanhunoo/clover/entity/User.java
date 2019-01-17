package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User extends BaseEntity {
    private Integer id;
    //最后登陆时间
    private String passWord;
    //最后登陆时间
    private String userName;
    //真实姓名
    private String realName;
    //手机号
    private String phone;
    //状态（1-启用;0-禁用）
    private Integer status;
    //所在店id(0-总;1-1号店)
    private Integer orgId;
    //备注
    private String remark;
    //最后登陆时间
    private LocalDateTime lastLoginTime;

    //----------冗余-----------//
    private String roleId;//角色
    private String roleName;//角色名称
    private String orgName;//所在店名称
}
