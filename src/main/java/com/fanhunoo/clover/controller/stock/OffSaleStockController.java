package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.entity.Dictionary;
import com.fanhunoo.clover.service.DictionaryService;
import com.fanhunoo.clover.service.StockService;
import com.fanhunoo.clover.util.Constant;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     * 数据接口--待下架查询
     */
    @ResponseBody
    @GetMapping("/list")
    public MyPage list(HttpServletRequest request) {
        //按时间段查询已入库的
        PageHelper.startPage(request);
        //List<User> users = stockService.findUsersBy(null,userDetails.getOrgId());
        return new MyPage(null);
    }



}
