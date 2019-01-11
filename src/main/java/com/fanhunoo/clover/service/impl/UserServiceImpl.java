package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.UserDao;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public List<User> findUsersBy(String selectAll,Integer orgId) {
        return userDao.findUsersBy(selectAll,orgId);
    }
}
