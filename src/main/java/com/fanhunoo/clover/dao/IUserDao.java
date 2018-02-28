package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    User findUserByName(String username);

    List<User> findUsersBy(Integer orgId);
}
