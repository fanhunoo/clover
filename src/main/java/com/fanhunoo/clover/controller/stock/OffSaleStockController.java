package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.Dictionary;
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
 * 库存管理--下架管理
 */
@Controller
@RequestMapping("/stock/offSale")
public class OffSaleStockController {
    @Resource
    private StockService stockService;
    @Autowired
    private DictionaryService dictionaryService;
    /**
     * 页面
     */
    @GetMapping("/")
    public String page(HttpServletRequest request) {
        String offSaleBatchId = new SimpleDateFormat("yyMMddHHmmssSSS").format((new Date()));
        request.setAttribute("offSaleBatchId","XJ"+offSaleBatchId);
        List<Dictionary> goodsSits = dictionaryService.selectByTitle(Constant.DICTIONARY_TITLE_GOODS_SIT);
        request.setAttribute("goodsSits",goodsSits);
        return "stock/offSaleStock/index";
    }

    /**
     * 下架
     */
    @ResponseBody
    @PostMapping("/")
    public Result offSale(HttpServletRequest request) {
        Result result = new Result();
        try {
            String data = request.getParameter("data");
            String offSaleBatchId = request.getParameter("offSaleBatchId");
            if(StringUtils.isEmpty(offSaleBatchId)){
                throw new RuntimeException("下架批次号为空！");
            }
            List<String> codes = CommonUtils.getObjectMapper().readValue(data,List.class);
            if(codes == null || codes.size()<1){
                throw new RuntimeException("待下架商品为空！");
            }
            Map<String,Object> param = new HashMap<>();
            param.put("offsaleBatchId",offSaleBatchId);
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            param.put("offsalePer",userDetails.getRealName());
            param.put("updatePer",userDetails.getRealName());
            param.put("orgId",userDetails.getOrgId());
            param.put("codes",codes);
            param.put("quantity",codes.size());
            param.put("status",Constant.COMMODITY_STATUS_OFF_SALE);
            param.put("statusBefore",Constant.COMMODITY_STATUS_ON_SALE);
            param.put("siteCode",userDetails.getOrgId());
            param.put("siteName",userDetails.getOrgName());
            stockService.offSale(param);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("下架成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("下架出错！"+e.getMessage());
        }
        return result;
    }



}
