package com.fanhunoo.clover.service;

import java.util.Set;

public interface IRoleService {
    Set<String>  findRoleByUserId(Integer userId);
}
