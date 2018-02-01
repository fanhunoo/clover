package com.fanhunoo.clover.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IPermissionDao {
    Set<String> findPermissionByUserId(Integer userId);
}
