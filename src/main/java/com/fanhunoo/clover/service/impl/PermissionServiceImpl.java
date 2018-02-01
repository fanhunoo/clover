package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.IPermissionDao;
import com.fanhunoo.clover.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public Set<String> findPermissionByUserId(Integer userId) {
        return permissionDao.findPermissionByUserId(userId);
    }
}
