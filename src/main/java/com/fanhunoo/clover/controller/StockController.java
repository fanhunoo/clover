package com.fanhunoo.clover.controller;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.User;
import com.fanhunoo.clover.entity.vo.StockVo;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.IStockService;
import com.fanhunoo.clover.service.IUserService;
import com.fanhunoo.clover.util.Constant;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 库存管理
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    @Resource
    private IUserService userService;

    @Resource
    private IStockService stockService;

    /**
     * 页面--库存查询
     */
    @GetMapping("/queryStockPage")
    public String queryStockPage(HttpServletRequest request, HttpServletResponse response) {
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
        List<User> users = userService.findUsersBy(userDetails.getOrgId());
        return new MyPage(users);
    }

    /**
     * 页面--入库管理
     */
    @GetMapping("/acceptStockPage")
    public String acceptStockPage(HttpServletRequest request, HttpServletResponse response) {
        //获取登录的账户
        MyUserDetails userDetails = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("realname",userDetails.getRealName());
        //判断春夏秋冬
        Calendar cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH) + 1;
        String season = Constant.SEASON_WINTER;
        if(month>=3 && month<=5){
            season = Constant.SEASON_SPRING;
        }else if(month>=6 && month<=8){
            season = Constant.SEASON_SUMMER;
        }else if(month>=9 && month<=11){
            season = Constant.SEASON_AUTUMN;
        }
        //入库批次号
        String stockBatchId = new SimpleDateFormat("yyMMddHHmmssSSS").format((new Date()));
        request.setAttribute("stockBatchId",stockBatchId);
        request.setAttribute("season",season);
        //类别编码
        int stockType = 0;
        try {
            stockType = stockService.getStockTypeSeq();
        } catch (Exception e) {
            e.printStackTrace();
            //todo
        }
        request.setAttribute("stockType",stockType<10 ? "0"+stockType : ""+stockType);
        return "stock/acceptStock/index";
    }

    /**
     * 接口--获取类别编码
     */
    @GetMapping("/getStockType")
    @ResponseBody
    public Result getStockType() {
        try {
            Map<String,Object> map = new HashMap<>(16);
            int stockType = stockService.getStockTypeSeq();
            map.put("stockType",stockType<10 ? "0"+stockType : ""+stockType);
            return new Result(Constant.SUCCESS,"成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.FAILURE,"获取类别编码异常"+e.getMessage(),null);
        }

    }

    /**
     * 接口--入库操作
     */
    @PostMapping("/addStock")
    @ResponseBody
    @Transactional(propagation= Propagation.REQUIRED)
    public Result addStock(HttpServletRequest request, HttpServletResponse response, StockVo stockVo) {
        try{
            String beforeCode = stockVo.getBeforeCode();
            /*如果有beforecode，表示是编辑过后的入库，先删除之前的code*/
            if(StringUtils.isNotEmpty(beforeCode)){
                beforeCode = beforeCode.substring(1,beforeCode.length()-7);
                stockService.deleteStock(beforeCode);
            }
            stockService.saveStock(stockVo);
            return new Result(Constant.SUCCESS,"成功",null);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.FAILURE,"入库操作异常"+e.getMessage(),null);
        }
    }

    /**
     * 页面--上架管理
     */
    @GetMapping("/onSaleStockPage")
    public String onSaleStockPage(HttpServletRequest request, HttpServletResponse response) {
        return "system/user/user";
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
