package com.fanhunoo.clover.service;

import java.util.Set;

public interface IPermissionService {
    Set<String> findPermissionByUserId(Integer userId);
}
