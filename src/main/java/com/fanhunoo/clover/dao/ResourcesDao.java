package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.Resources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResourcesDao {
    List<Resources> queryAll();

    List<Resources> queryByCondition(Map<String,Object> param);

    List<Resources> loadMenu(@Param("username")String userName,@Param("type")Integer type,@Param("parentId")String parentId);
    List<Resources> selectByRoleId(String roleId);

    void saveResource(Resources resources);
    void updateResource(Resources resources);
    void deleteResource(String id);
    void deleteResourceContainChild(String id);
    void deletePermissionByResourceId(String id);
    void deletePermissionByRoleId(String roleId);
    void savePermissions(List<Map<String, String>> permissions);
}
