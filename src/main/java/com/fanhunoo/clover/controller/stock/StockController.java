package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.StockService;
import com.fanhunoo.clover.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 库存管理--入库管理
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    @Resource
    private UserService userService;

    @Resource
    private StockService stockService;

    /**
     * 页面--库存查询
     */
    @GetMapping("/queryStockPage")
    public String queryStockPage() {
        return "system/user/user";
    }

    /**
     * 数据接口--库存查询
     */
    @ResponseBody
    @GetMapping("/queryStock")
    public MyPage queryStock(HttpServletRequest request) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String roleId = userDetails.getRoleId();
        PageHelper.startPage(request);
        List<User> users = userService.findUsersBy(null,userDetails.getOrgId());
        return new MyPage(users);
    }

    /**
     * 页面--上架管理
     */
    @GetMapping("/onSaleStockPage")
    public String onSaleStockPage(HttpServletRequest request, HttpServletResponse response) {
        return "stock/onSaleStock/index";
    }

    /**
     * 页面--下架管理
     */
    @GetMapping("/offSaleStockPage")
    public String offSaleStockPage(HttpServletRequest request, HttpServletResponse response) {
        return "system/user/user";
    }
    /**
     * 页面--转移管理
     */
    @GetMapping("/moveStockPage")
    public String moveStockPage(HttpServletRequest request, HttpServletResponse response) {

        return "stock/moveStock/index";
    }

    /**
     * 页面--报损管理
     */
    @GetMapping("/damageStockPage")
    public String damageStockPage(HttpServletRequest request, HttpServletResponse response) {
        return "system/user/user";
    }

}
