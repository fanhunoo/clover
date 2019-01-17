package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;
import lombok.Data;

@Data
public class Role extends BaseEntity {
    /**
     * guid
     */
    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 级别（0-系统管理员；10-boss；20-店长）
     */
    private Integer rank;
    /**
     * 状态
     */
    private Integer status;

}
