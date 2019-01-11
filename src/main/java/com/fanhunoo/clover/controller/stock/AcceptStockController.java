package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.vo.StockVo;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.StockService;
import com.fanhunoo.clover.service.UserService;
import com.fanhunoo.clover.util.Constant;
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
 * 库存管理--入库管理
 */
@Controller
@RequestMapping("/stock/acceptStock")
public class AcceptStockController {

    @Resource
    private UserService userService;

    @Resource
    private StockService stockService;

    /**
     * 页面--入库管理
     */
    @GetMapping("/")
    public String acceptStock(HttpServletRequest request, HttpServletResponse response) {
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
        int stockType;
        try {
            stockType = stockService.getStockTypeSeq();
        } catch (Exception e) {
            e.printStackTrace();
            return "error/500";
        }
        request.setAttribute("stockType",stockType<10 ? "0"+stockType : ""+stockType);
        return "stock/acceptStock/index";
    }

    /**
     * 接口--获取类别编码
     */
    @GetMapping("/type")
    @ResponseBody
    public Result type() {
        try {
            Map<String,Object> map = new HashMap<>(16);
            int stockType = stockService.getStockTypeSeq();
            map.put("stockType",stockType<10 ? "0"+stockType : ""+stockType);
            return new Result(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.FAILURE,"获取类别编码异常"+e.getMessage(),null);
        }

    }

    /**
     * 接口--入库操作
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(HttpServletRequest request, HttpServletResponse response, StockVo stockVo) {
        try{
            stockService.saveStock(stockVo);
            return new Result(Constant.SUCCESS,"成功",null);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.FAILURE,"入库操作异常"+e.getMessage(),null);
        }
    }



}
