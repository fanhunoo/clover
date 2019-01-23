package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.CommodityDetail;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.DictionaryService;
import com.fanhunoo.clover.service.StockService;
import com.fanhunoo.clover.util.CommonUtils;
import com.fanhunoo.clover.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 库存明细
 */
@Controller
@RequestMapping("/stock/detail")
public class StockDetailController {
    @Resource
    private StockService stockService;
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 查询商品信息（下架、转移管理）
     */
    @GetMapping("/{code}")
    @ResponseBody
    public Result selectCommodityDetail(@PathVariable String code) {
        Result result = new Result();
        try {
            if(StringUtils.isEmpty(code)){
                throw new RuntimeException("商品编码为空！");
            }
            CommodityDetail param = new CommodityDetail();
            param.setCode(code);
            param.setStatus(Constant.COMMODITY_STATUS_ON_SALE);
            List<CommodityDetail> commodityDetails = stockService.selectStock(param);
            if(commodityDetails==null || commodityDetails.size()<1){
                throw new RuntimeException("未查到对应商品信息！");
            }
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!CommonUtils.checkSpecialPermission(userDetails.getRoleId()) &&
                    !StringUtils.equals(commodityDetails.get(0).getSiteCode(),String.valueOf(userDetails.getOrgId()))){
                throw new RuntimeException("该商品不在当前店铺里！");
            }
            result.setData(commodityDetails.get(0));
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("查询商品信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("查询商品信息出错！"+e.getMessage());
        }
        return result;
    }




}
