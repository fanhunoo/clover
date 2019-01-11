package com.fanhunoo.clover.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品信息实例
 */
@Data
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
    private LocalDateTime storageTime;
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
    private LocalDateTime updateTime;
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
    /**
     * 上架批次号
     */
    private String onsaleBatchId;
}
