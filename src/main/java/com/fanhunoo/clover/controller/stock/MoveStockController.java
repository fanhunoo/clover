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
 * 库存管理--转移管理
 */
@Controller
@RequestMapping("/stock/move")
public class MoveStockController {
    @Resource
    private StockService stockService;
    @Autowired
    private DictionaryService dictionaryService;
    /**
     * 页面
     */
    @GetMapping("/")
    public String page(HttpServletRequest request) {
        String moveBatchId = new SimpleDateFormat("yyMMddHHmmssSSS").format((new Date()));
        request.setAttribute("moveBatchId","ZY"+moveBatchId);
        List<Dictionary> orgs = dictionaryService.selectByTitle(Constant.DICTIONARY_TITLE_ORG);
        request.setAttribute("orgs",orgs);
        return "stock/moveStock/index";
    }

    /**
     * 下架
     */
    @ResponseBody
    @PostMapping("/")
    public Result move(HttpServletRequest request) {
        Result result = new Result();
        try {
            String data = request.getParameter("data");
            String moveBatchId = request.getParameter("moveBatchId");
            String orgId = request.getParameter("orgId");
            String orgName = request.getParameter("orgName");
            if(StringUtils.isEmpty(moveBatchId)){
                throw new RuntimeException("转移批次号为空！");
            }
            List<String> codes = CommonUtils.getObjectMapper().readValue(data,List.class);
            if(codes == null || codes.size()<1){
                throw new RuntimeException("待转移商品为空！");
            }
            Map<String,Object> param = new HashMap<>();
            param.put("moveBatchId",moveBatchId);
            MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            param.put("movePer",userDetails.getRealName());
            param.put("updatePer",userDetails.getRealName());
            param.put("codes",codes);
            param.put("quantity",codes.size());
            param.put("statusBefore",Constant.COMMODITY_STATUS_ON_SALE);
            param.put("resourceCode",userDetails.getOrgId());
            param.put("resourceName",userDetails.getOrgName());
            param.put("siteCode",orgId);
            param.put("siteName",orgName);
            param.put("targetCode",orgId);
            param.put("targetName",orgName);
            stockService.move(param);
            result.setStatusCode(Constant.SUCCESS);
            result.setMessage("转移成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(Constant.FAILURE);
            result.setMessage("转移出错！"+e.getMessage());
        }
        return result;
    }



}
