package com.fanhunoo.clover.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 编码（名称编码+序号=编码）
     */
    private String code;
    /**
     * 名称编码
     */
    private String nameCode;
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
     * 性别款式（9-中性;1-男性;0-女性)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    private String updatePer;
    /**
     * 进价
     */
    private Double purchasePrice;
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
    /**
     * 下架批次号
     */
    private String offsaleBatchId;
    /**
     * 转移批次号
     */
    private String moveBatchId;

    //冗余：用于构建treeTable
    private String parentId;
}
