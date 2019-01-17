package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.RoleDao;
import com.fanhunoo.clover.dao.UserDao;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public List<User> findUsersBy(String selectAll,Integer orgId) {
        return userDao.findUsersBy(selectAll,orgId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveUser(User user) throws Exception{
        userDao.saveUser(user);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateUser(User user) throws Exception{
        userDao.updateUser(user);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteUserById(String id) throws Exception{
        userDao.deleteUserById(id);
        //删除对应角色认证信息
        userDao.deleteAuthByUserId(id);
    }
}
