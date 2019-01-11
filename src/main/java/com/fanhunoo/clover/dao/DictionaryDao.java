package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryDao {
    List<Dictionary> findAllRole();
}
