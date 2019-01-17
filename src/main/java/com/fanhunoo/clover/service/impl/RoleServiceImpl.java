package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.RoleDao;
import com.fanhunoo.clover.entity.Role;
import com.fanhunoo.clover.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> selectAll() {
        return roleDao.selectAll();
    }

    @Override
    public List<Role> selectByRoleIdLimitRank(String roleId) {
        return roleDao.selectByRoleIdLimitRank(roleId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveRole(Role role) throws Exception{
        roleDao.saveRole(role);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateRole(Role role) throws Exception{
        roleDao.updateRole(role);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteRoleById(String id) throws Exception{
        roleDao.deleteRoleById(id);
        //删除对应的权限信息
        roleDao.deletePermissionByRoleId(id);
    }
}
