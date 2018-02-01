package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.Resources;

import java.util.List;

public interface IResourcesService {
    List<Resources> loadMenu(String userName);
}
