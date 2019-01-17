package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * 系统资源表-实体类
 */
@Data
public class Resources extends BaseEntity {
    /**
     * 主键（uuid）
     */
    private String id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源访问地址
     */
    private String url;
    /**
     * 备注
     */
    private String remark;
    /**
     * 资源类型（1-菜单;2-按钮;3-url）
     */
    private Integer type;
    /**
     * 父级资源id
     */
    private String parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;


    /**
     * 子菜单的json
     */
    private String childJson;

    //子资源-用于构建资源树
    private List<Resources> children;
}
