package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectAll();
    List<Role> selectByRoleIdLimitRank(String roleId);
    void saveRole(Role role) throws Exception;
    void updateRole(Role role)throws Exception;
    void deleteRoleById(String id)throws Exception;
}
