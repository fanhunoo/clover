package com.fanhunoo.clover.entity;

import lombok.Data;


/**
 * 字典信息实例
 */
@Data
public class Dictionary {
    /**
     * 主键guid
     */
    private String id;
    /**
     * 字典编码
     */
    private String titleCode;
    /**
     * 字典名称
     */
    private String titleValue;
    /**
     * 字典项编码
     */
    private String code;
    /**
     * 字典项值
     */
    private String value;
    /**
     * 状态（1-启用;0-禁用）
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

}
