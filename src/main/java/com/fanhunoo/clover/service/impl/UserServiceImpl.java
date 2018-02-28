package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.IUserDao;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public List<User> findUsersBy(Integer orgId) {
        return userDao.findUsersBy(orgId);
    }
}
