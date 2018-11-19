package com.fanhunoo.clover.entity;


import java.util.Date;

/**
 * 商品信息实例
 */
public class CommodityDetail {
    /**
     * 商品唯一编
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称编码（名称编码+序号=编码）
     */
    private String code;
    /**
     * 定价
     */
    private Double salePrice;
    /**
     * 季节款式（'C'-春;'X'-夏;'Q'-秋;'D'-冬）
     */
    private String season;
    /**
     * 尺码（SMLX)
     */
    private String sizeType;
    /**
     * 性别款式（0-中性;1-男性;2-女性)
     */
    private String sex;
    /**
     * 状态（100-已入库;101=已上架;102-已售出;103-已下架;104-已损耗）
     */
    private Integer status;
    /**
     * 来源编码
     */
    private String resourceCode;
    /**
     * 来源名称（厂家、丢失牌、重新上架等）
     */
    private String resourceName;
    /**
     * 入库时间
     */
    private Date storageTime;
    /**
     * 入库操作员
     */
    private String storagePer;
    /**
     * 入库批次号
     */
    private String storageBatchId;
    /**
     * 序号
     */
    private Integer sortId;
    /**
     * 存储地址编码
     */
    private String siteCode;
    /**
     * 存储地址名称（1号店、2号店、3号店、仓库等）
     */
    private String siteName;
    /**
     * 备注
     */
    private String remake;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updatePer;
    /**
     * 进价
     */
    private String purchasePrice;
    /**
     * 预览图
     */
    private String image;
    /**
     * 下架前编码
     */
    private String beforeCode;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getStoragePer() {
        return storagePer;
    }

    public void setStoragePer(String storagePer) {
        this.storagePer = storagePer;
    }

    public String getStorageBatchId() {
        return storageBatchId;
    }

    public void setStorageBatchId(String storageBatchId) {
        this.storageBatchId = storageBatchId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
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

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getUpdatePer() {
        return updatePer;
    }

    public void setUpdatePer(String updatePer) {
        this.updatePer = updatePer;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBeforeCode() {
        return beforeCode;
    }

    public void setBeforeCode(String beforeCode) {
        this.beforeCode = beforeCode;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
