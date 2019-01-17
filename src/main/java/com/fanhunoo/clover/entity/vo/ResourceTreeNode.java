package com.fanhunoo.clover.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 入库信息vo
 */
@Getter
@Setter
public class ResourceTreeNode {
    private String id;
    private String name;
    private String parentId;
    private List<ResourceTreeNode> children;

    public ResourceTreeNode(String id,String name,String parentId){
         this.id = id;
         this.name = name;
         this.parentId = parentId;
    }
}
