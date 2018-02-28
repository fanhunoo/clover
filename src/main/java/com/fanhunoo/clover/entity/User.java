package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;

import java.util.Date;

public class User extends BaseEntity {
    private Integer id;
    //最后登陆时间
    private String passWord;
    //最后登陆时间
    private String userName;
    //真实姓名
    private String realName;
    //手机号
    private Integer phone;
    //状态（1-启用;0-禁用）
    private Integer status;
    //所在店id(0-总;1-1号店)
    private Integer orgId;
    //备注
    private String remarkes;
    //最后登陆时间
    private Date lastLoginTime;


    //----------冗余-----------//
    private String roleId;//角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getRemarkes() {
        return remarkes;
    }

    public void setRemarkes(String remarkes) {
        this.remarkes = remarkes;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
