package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.DictionaryDao;
import com.fanhunoo.clover.entity.Dictionary;
import com.fanhunoo.clover.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public List<Dictionary> findAllRole() {
        return dictionaryDao.findAllRole();
    }
}
