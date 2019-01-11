package com.fanhunoo.clover.dao;

import com.fanhunoo.clover.entity.CommodityDetail;
import com.fanhunoo.clover.entity.vo.StockVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDao {
    int getStockTypeSeq() throws Exception;

    void deleteStorageLog(String nameCode) throws Exception;

    void deleteCommodityDetail(String nameCode) throws Exception;

    void saveStorageLog(StockVo stockVo) throws Exception;

    void saveCommodityDetailList(  List<CommodityDetail> commodityDetails) throws Exception;


}
