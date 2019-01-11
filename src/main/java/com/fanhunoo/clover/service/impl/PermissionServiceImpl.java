package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.PermissionDao;
import com.fanhunoo.clover.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Set<String> findPermissionByUserId(Integer userId) {
        return permissionDao.findPermissionByUserId(userId);
    }
}
