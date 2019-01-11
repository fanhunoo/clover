package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;
import lombok.Data;


/**
 * 系统资源表-实体类
 */
@Data
public class StorageLog extends BaseEntity {
    /**
     * 主键uuid
     */
    private String id;
    /**
     * 入库批次号
     */
    private String storageBatchId;
    /**
     * 商品名称编码
     */
    private String code;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 操作时间
     */
    private String operateTime;
    /**
     * 定价
     */
    private Double salePrice;
    /**
     * 来源编码
     */
    private String resourceCode;
    /**
     * 来源名称
     */
    private String resourceName;
    /**
     * 存放地址编码
     */
    private String siteCode;
    /**
     * 存放地址名称
     */
    private String siteName;
    /**
     * 下架入库单号（下架商品重新上架记录之前的入库单号）
     */
    private String beforeStorageBatchId;
}
