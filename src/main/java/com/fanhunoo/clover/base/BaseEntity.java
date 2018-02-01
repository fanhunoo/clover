package com.fanhunoo.clover.base;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    //创建时间
    private Date createTime;
    //创建人
    private String createPer;
    //更新时间
    private Date updateTime;
    //更新人
    private String updatePer;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePer() {
        return createPer;
    }

    public void setCreatePer(String createPer) {
        this.createPer = createPer;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePer() {
        return updatePer;
    }

    public void setUpdatePer(String updatePer) {
        this.updatePer = updatePer;
    }
}
