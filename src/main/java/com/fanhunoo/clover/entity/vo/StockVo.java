package com.fanhunoo.clover.entity.vo;

import lombok.Data;

/**
 * 入库信息vo
 */
@Data
public class StockVo{
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 季节
     */
    private String season;
    /**
     * 来源
     */
    private String resource;
    /**
     * 件数
     */
    private Integer jianshu;
    /**
     * 名称编码
     */
    private String code;
    /**
     * 尺寸
     */
    private String sizeType;
    /**
     * 性别款式
     */
    private String sex;
    /**
     * 存放地点
     */
    private String storage;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 备注
     */
    private String remark;
    /**
     * 编辑之前的名称编码
     */
    private String beforeCode;
    /**
     * 入库批次号
     */
    private String stockBatchId;
    /**
     * 进价
     */
    private Double purchasePrice;
    /**
     * 定价
     */
    private Double salePrice;
}
