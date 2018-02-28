package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.Resources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResourcesDao {
    List<Resources> queryAll();

    List<Resources> loadMenu(@Param("username")String userName,@Param("type")Integer type,@Param("parentId")String parentId);
}
