package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.service.StockService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 库存管理--报损管理
 */
//@Controller
@RequestMapping("/stock/damage")
public class DamageStockController {
    @Resource
    private StockService stockService;

    /**
     * 页面
     */
    @GetMapping("/")
    public String page() {
        return "stock/damageStock/index";
    }

    /**
     * 数据接口--待转移查询
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
