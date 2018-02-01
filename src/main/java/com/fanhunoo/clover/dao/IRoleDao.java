package com.fanhunoo.clover.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IRoleDao {
    Set<String> findRoleByUserId(Integer userId);
}
