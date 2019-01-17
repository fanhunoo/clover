package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> selectByTitle(String titleCode);
}
