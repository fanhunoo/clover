package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.Resources;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IResourcesDao {
    Set<String> findPermissionByUserId(Integer userId);

    @Select("select * from T_SYS_RESOURCES")
    List<Resources> queryAll();

    @Select("select re.* from T_SYS_RESOURCES re,t_sys_permission rr,t_sys_auth ur,t_sys_user u " +
            "WHERE re.id = rr.resources_Id AND rr.role_Id = ur.role_Id " +
            "AND ur.user_Id =u.id AND u.user_name=#{username} ")
    List<Resources> loadMenu(String userName);
}
