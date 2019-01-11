package com.fanhunoo.clover.service;

import java.util.Set;

public interface PermissionService {
    Set<String> findPermissionByUserId(Integer userId);
}
