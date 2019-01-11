package com.fanhunoo.clover.service;

import com.fanhunoo.clover.entity.vo.StockVo;

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
}
