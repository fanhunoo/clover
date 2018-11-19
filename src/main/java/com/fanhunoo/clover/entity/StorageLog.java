package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * 系统资源表-实体类
 */
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
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新人
     */
    private String updatePer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStorageBatchId() {
        return storageBatchId;
    }

    public void setStorageBatchId(String storageBatchId) {
        this.storageBatchId = storageBatchId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBeforeStorageBatchId() {
        return beforeStorageBatchId;
    }

    public void setBeforeStorageBatchId(String beforeStorageBatchId) {
        this.beforeStorageBatchId = beforeStorageBatchId;
    }

}
