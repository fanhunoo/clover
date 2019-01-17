package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.ResourcesDao;
import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesDao resourcesDao;

    @Override
    public List<Resources> queryAll() {
        return resourcesDao.queryAll();
    }

    @Override
    public List<Resources> queryByCondition(Map<String, Object> param) {
        return resourcesDao.queryByCondition(param);
    }

    @Override
    public List<Resources> loadMenu(String userName,Integer type,String parentId) {
        return resourcesDao.loadMenu(userName,type,parentId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveResource(Resources resources) throws Exception{
        resourcesDao.saveResource(resources);
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateResource(Resources resources) throws Exception{
        resourcesDao.updateResource(resources);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteResource(String id) throws Exception {
        //删除当前资源(包含子资源)
        resourcesDao.deleteResourceContainChild(id);
        //删除对应权限
        resourcesDao.deletePermissionByResourceId(id);
    }

    @Override
    public List<Resources> selectByRoleId(String roleId) {
        return resourcesDao.selectByRoleId(roleId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updatePermission(String roleId, List<Map<String, String>> permissions) throws Exception {
        //删除当前权限关系
        resourcesDao.deletePermissionByRoleId(roleId);
        //保存新的权限关系
        resourcesDao.savePermissions(permissions);
    }
}
