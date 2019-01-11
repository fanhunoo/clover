package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.User;

import java.util.List;

public interface UserService {
    User findUserByName(String userName);
    List<User> findUsersBy(String selectAll,Integer orgId);
}
