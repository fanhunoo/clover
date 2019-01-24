package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.CommodityDetail;
import com.fanhunoo.clover.entity.vo.StockVo;

import java.util.List;
import java.util.Map;

public interface StockService {
    /**
     * 获取商品类别编码
     * @return int
     */
    int getStockTypeSeq() throws Exception;

    /**
     * 删除入库信息和商品库存信息
     */
    void deleteStock(String nameCode) throws Exception;

    /**
     * 保存入库信息
     * @param stockVo
     */
    void saveStock(StockVo stockVo) throws Exception;

    /**
     * 查询库存信息
     */
    List<CommodityDetail> selectStock(Map<String,Object> param);

    void onSale(Map<String,Object> param)throws Exception;

    void offSale(Map<String,Object> param)throws Exception;
    void move(Map<String,Object> param)throws Exception;
}
