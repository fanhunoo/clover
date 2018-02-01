package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.IRoleDao;
import com.fanhunoo.clover.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public Set<String> findRoleByUserId(Integer userId) {
        return roleDao.findRoleByUserId(userId);
    }
}
