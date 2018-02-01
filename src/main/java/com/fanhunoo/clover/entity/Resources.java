package com.fanhunoo.clover.entity;

import com.fanhunoo.clover.base.BaseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 系统资源表-实体类
 */
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
    private String remarkes;
    /**
     * 资源类型（1-菜单;2-按钮）
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
     * 资源key
     */
    private String key;
    /**
     * 图标
     */
    private String icon;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemarkes() {
        return remarkes;
    }

    public void setRemarkes(String remarkes) {
        this.remarkes = remarkes;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("df");
        System.out.println("-------------------------"+encode);
    }
}
