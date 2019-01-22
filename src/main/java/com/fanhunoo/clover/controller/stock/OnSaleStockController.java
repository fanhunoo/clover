package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.CommodityDetail;
import com.fanhunoo.clover.entity.Dictionary;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.DictionaryService;
import com.fanhunoo.clover.service.StockService;
import com.fanhunoo.clover.util.CommonUtils;
import com.fanhunoo.clover.util.Constant;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存管理--上架管理
 */
@Controller
@RequestMapping("/stock/onSale")
public class OnSaleStockController {
    @Resource
    private StockService stockService;
    @Autowired
    private DictionaryService dictionaryService;
    /**
     * 页面
     */
    @GetMapping("/")
    public String page(HttpServletRequest request) {
        String onSaleBatchId = new SimpleDateFormat("yyMMddHHmmssSSS").format((new Date()));
        request.setAttribute("onSaleBatchId","SJ"+onSaleBatchId);
        List<Dictionary> orgs = dictionaryService.selectByTitle(Constant.DICTIONARY_TITLE_ORG);
        request.setAttribute("orgs",orgs);
        return "stock/onSaleStock/index";
    }

    /**
     * 数据接口--待上架查询
     */
    @ResponseBody
    @GetMapping("/list")
    public MyPage list(HttpServletRequest request,CommodityDetail commodityDetail) {
        //查询已入库的
        commodityDetail.setStatus(Constant.COMMODITY_STATUS_STORAGED);
        PageHelper.startPage(request);
        List<CommodityDetail> commodityDetails = stockService.selectStock(commodityDetail);
        return new MyPage(commodityDetails);
    }


    /**
     * 上架
     */
    @PostMapping("/")
    @ResponseBody
    public Result onSale(HttpServletRequest request){
        Result result = new Result();
        try {
            String orgId = request.getParameter("orgId");
            String data = request.getParameter("data");
            String onSaleBatchId = request.getParameter("onSaleBatchId");
            if(StringUtils.isEmpty(orgId)){
                throw new RuntimeException("上架店铺为空！");
            }
            if(StringUtils.isEmpty(onSaleBatchId)){
                throw new RuntimeException("上架批次号为空！");
            }
            List<Map<String,String>> stocks = CommonUtils.getObjectMapper().readValue(data,List.class);
            if(stocks == null || stocks.size()<1){
                throw new RuntimeException("上架商品为空！");
            }
            Map<String,Object> param = new HashMap<>();
            param.put("orgId",orgId);
            param.put("onsaleBatchId",onSaleBatchId);
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            param.put("onsalePer",userDetails.getRealName());
            param.put("stocks",stocks);
            param.put("quantity",stocks.size());
            param.put("resourceCode",stocks.get(0).get("siteCode"));
            param.put("resourceName",stocks.get(0).get("siteName"));
            param.put("status",Constant.COMMODITY_STATUS_ON_SALE);
            stockService.onSale(param);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("上架成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("上架出错！"+e.getMessage());
        }
        return result;
    }

}
