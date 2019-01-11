package com.fanhunoo.clover.service.impl;

import com.fanhunoo.clover.dao.StockDao;
import com.fanhunoo.clover.entity.CommodityDetail;
import com.fanhunoo.clover.entity.vo.StockVo;
import com.fanhunoo.clover.service.StockService;
import com.fanhunoo.clover.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;


    @Override
    public int getStockTypeSeq()  throws Exception{
        return stockDao.getStockTypeSeq();
    }

    @Override
    public void deleteStock(String nameCode) throws Exception {
        stockDao.deleteStorageLog(nameCode);
        stockDao.deleteCommodityDetail(nameCode);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void saveStock(StockVo stockVo) throws Exception {
        String beforeCode = stockVo.getBeforeCode();
        /*如果有beforecode，表示是编辑过后的入库，先删除之前的code*/
        if(StringUtils.isNotEmpty(beforeCode)){
            beforeCode = beforeCode.substring(1,beforeCode.length()-7);
            this.deleteStock(beforeCode);
        }
        stockDao.saveStorageLog(stockVo);
        stockDao.saveCommodityDetailList(this.makeCommodityDetails(stockVo));
    }

    /**
     * 根据stockVo生成商品详细实例
     * @param stockVo
     * @return
     */
    private List<CommodityDetail> makeCommodityDetails(StockVo stockVo){
        List<CommodityDetail> commodityDetails = new ArrayList<>(stockVo.getJianshu());
        String code = stockVo.getCode().substring(0,stockVo.getCode().indexOf("~")-3);
        BeanCopier copier = BeanCopier.create(StockVo.class, CommodityDetail.class,false);
        for(int i = 1;i <= stockVo.getJianshu();i++){
            CommodityDetail commodityDetail = new CommodityDetail();
            copier.copy(stockVo, commodityDetail, null);
            if(i <10){
                commodityDetail.setCode(code+"00"+i);
            }else if(i <100){
                commodityDetail.setCode(code+"0"+i);
            }else{
                commodityDetail.setCode(code+i);
            }
            commodityDetail.setId(null);
            commodityDetail.setStatus(Constant.COMMODITY_STATUS_STORAGED);
            commodityDetail.setResourceCode(stockVo.getResource());
            commodityDetail.setResourceName(stockVo.getResource());//todo
            commodityDetail.setStorageTime(LocalDateTime.now());
            commodityDetail.setStoragePer(stockVo.getOperator());
            commodityDetail.setSortId(i);
            commodityDetail.setSiteCode(stockVo.getStorage());
            commodityDetail.setSiteName(stockVo.getStorage());//todo
            commodityDetails.add(commodityDetail);
        }
        return commodityDetails;
    }
}
