package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.User;

import java.util.List;

public interface IUserService {
    User findUserByName(String userName);
    List<User> findUsersBy(Integer orgId);
}
