package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.IResourcesDao;
import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements IResourcesService {
    @Autowired
    private IResourcesDao resourcesDao;

    @Override
    public List<Resources> loadMenu(String userName) {
        return resourcesDao.loadMenu(userName);
    }
}
