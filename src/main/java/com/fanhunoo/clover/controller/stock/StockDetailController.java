package com.fanhunoo.clover.controller.stock;

import com.fanhunoo.clover.base.MyPage;
import com.fanhunoo.clover.base.Result;
import com.fanhunoo.clover.entity.CommodityDetail;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
     * 页面
     */
    @GetMapping("/")
    public String page(HttpServletRequest request) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //特殊权限才展示已入库的商品
        if(CommonUtils.checkSpecialPermission(userDetails.getRoleId())){
            request.setAttribute("containStoraged","Y");
        }
        return "stock/stockDetail/index";
    }

    /**
     * 查询商品信息（下架、转移管理）
     */
    @GetMapping("/list")
    @ResponseBody
    public MyPage list(@RequestParam(required = false)String code,
                       @RequestParam(required = false)String name,
                       @RequestParam(required = false)String season,
                       @RequestParam(required = false)String status,
                       HttpServletRequest request){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,Object> param = new HashMap<>();
        //非特殊权限不展示已入库的商品,并且需按orgId作为存储地点条件查询
        if(!CommonUtils.checkSpecialPermission(userDetails.getRoleId())){
            param.put("containStoraged","N");
            param.put("siteCode",userDetails.getOrgId());
        }
        if(StringUtils.isNotEmpty(code) && !"".equals(code.trim())){
            param.put("code",code);
        }
        if(StringUtils.isNotEmpty(name) && !"".equals(name.trim())){
            param.put("nameLike",name);
        }
        if(StringUtils.isNotEmpty(season)){
            param.put("season",season);
        }
        if(StringUtils.isNotEmpty(status)){
            param.put("status",Integer.valueOf(status));
        }
        PageHelper.startPage(request);
        List<CommodityDetail> commodityDetails = stockService.selectStock(param);
        long size = (long) commodityDetails.size();
        //构建treeTable
        List<CommodityDetail> parents = new ArrayList<>();
        Map<String,String> nameCodesMap = new HashMap<>();
        for(CommodityDetail d : commodityDetails){
            if(nameCodesMap.containsKey(d.getNameCode())){
                d.setParentId(nameCodesMap.get(d.getNameCode()));
            }else{
                CommodityDetail treeNode = new CommodityDetail();
                String uuid = UUID.randomUUID().toString();
                nameCodesMap.put(d.getNameCode(),uuid);
                treeNode.setId(uuid);
                treeNode.setCode(d.getNameCode());
                treeNode.setName(d.getName());
                treeNode.setSeason(d.getSeason());
                treeNode.setSalePrice(d.getSalePrice());
                parents.add(treeNode);
                d.setParentId(nameCodesMap.get(d.getNameCode()));
            }
        }
        commodityDetails.addAll(parents);
        return new MyPage(Constant.PAGE_SUCCESS,"", size,commodityDetails);
    }

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
            Map<String,Object> param = new HashMap<>();
            param.put("code",code);
            param.put("status",Constant.COMMODITY_STATUS_ON_SALE);
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
