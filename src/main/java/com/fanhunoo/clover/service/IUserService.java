package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.User;

public interface IUserService {
    User selectUser(Integer id);
    User findUserByName(String userName);
    User selectLogin(String userName, String passWord);
}
