package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IUserDao {
    User selectUser(Integer id);
    User selectLogin(Map<String, String> params);

    User findUserByName(String username);
}
