package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User findUserByName(String username);

    List<User> findUsersBy(@Param("selectAll")String selectAll,@Param("orgId")Integer orgId);
}
