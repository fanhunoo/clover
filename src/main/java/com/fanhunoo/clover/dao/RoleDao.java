package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.Role;
import com.fanhunoo.clover.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    List<Role> selectAll();
    List<Role> selectByRoleIdLimitRank(String roleId);

    void saveRole(Role role);
    void updateRole(Role role);
    void deleteRoleById(String id);
    void deletePermissionByRoleId(String id);
}
