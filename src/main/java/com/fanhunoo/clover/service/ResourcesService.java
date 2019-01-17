package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.Resources;

import java.util.List;
import java.util.Map;

public interface ResourcesService {
    List<Resources> queryAll();
    List<Resources> queryByCondition(Map<String,Object> param);
    List<Resources> loadMenu(String userName,Integer type,String parentId);
    void saveResource(Resources resources) throws Exception;
    void updateResource(Resources resources) throws Exception;
    void deleteResource(String id) throws Exception;
    List<Resources> selectByRoleId(String roleId);
    void updatePermission(String roleId, List<Map<String,String>> permissions) throws Exception;
}
