package com.fanhunoo.clover.entity.vo;

/**
 * 入库信息vo
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getJianshu() {
        return jianshu;
    }

    public void setJianshu(Integer jianshu) {
        this.jianshu = jianshu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeforeCode() {
        return beforeCode;
    }

    public void setBeforeCode(String beforeCode) {
        this.beforeCode = beforeCode;
    }

    public String getStockBatchId() {
        return stockBatchId;
    }

    public void setStockBatchId(String stockBatchId) {
        this.stockBatchId = stockBatchId;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
}
