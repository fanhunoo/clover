package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.IUserDao;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User selectUser(Integer id) {
        return userDao.selectUser(id);
    }

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public User selectLogin(String userName, String passWord) {
        Map<String,String> params = new HashMap<>(16);
        params.put("userName",userName);
        params.put("passWord",passWord);
        return userDao.selectLogin(params);
    }




    /**
     * 加密密码
     */
    public void encryptPassword(User user){
        String password = user.getPassWord();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassWord(password);
    }
}
