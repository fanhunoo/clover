package com.fanhunoo.clover.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 常量类
 */
public class Constant {
    /**
     * 一级菜单
     */
    public static final Integer MENU_TOP = 1;
    /**
     * 二级菜单
     */
    public static final Integer MENU_SECOND = 2;
    /**
     * 三级菜单
     */
    public static final Integer MENU_THIRD = 3;

    /**
     * 超管角色
     */
    public static final String ROLE_SUPER_ADMIN = "super_admin";
    /**
     * 老板角色
     */
    public static final String ROLE_BOSS = "boss";

    /**
     * 请求成功状态码
     */
    public static final String SUCCESS = "200";

    /**
     * 请求失败状态码
     */
    public static final String FAILURE = "500";

    /**
     * 分页请求成功状态码
     */
    public static final String PAGE_SUCCESS = "0";

    /**
     * 季节编码--春季
     */
    public static final String SEASON_SPRING = "C";

    /**
     * 季节编码--夏季
     */
    public static final String SEASON_SUMMER = "X";

    /**
     * 季节编码--秋季
     */
    public static final String SEASON_AUTUMN = "Q";

    /**
     * 季节编码--冬季
     */
    public static final String SEASON_WINTER = "D";

    /**
     * 商品状态（已入库）
     */
    public static final int COMMODITY_STATUS_STORAGED = 100;

    /**
     * 商品状态（已上架）
     */
    public static final int COMMODITY_STATUS_ON_SALE = 101;

    /**
     * 商品状态（已售出）
     */
    public static final int COMMODITY_STATUS_SALED = 102;

    /**
     * 商品状态（已下架）
     */
    public static final int COMMODITY_STATUS_OFF_SALE = 103;

    /**
     * 商品状态（已损耗）
     */
    public static final int COMMODITY_STATUS_DAMAGE = 104;

    /**
     * 字典编码-所属机构
     */
    public static final String DICTIONARY_TITLE_ORG = "ORG";
    /**
     * 字典编码-货品来源
     */
    public static final String DICTIONARY_TITLE_GOODS_RESOURCE = "GOODS_RESOURCE";    /**
     * 字典编码-货品存放地点
     */
    public static final String DICTIONARY_TITLE_GOODS_SIT = "GOODS_SIT";


}
