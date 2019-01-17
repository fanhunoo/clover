package com.fanhunoo.clover.controller;

import com.fanhunoo.clover.entity.Resources;
import com.fanhunoo.clover.security.MyUserDetails;
import com.fanhunoo.clover.service.ResourcesService;
import com.fanhunoo.clover.util.CommonUtils;
import com.fanhunoo.clover.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页相关
 */
@Controller
public class HomePageController {

    @Autowired
    private ResourcesService resourcesService;

    /**
     * 跳转到首页
     */
    @RequestMapping("/")
    public String index(){
        return "redirect:login";
    }

    /**
     * 跳转到首页
     */
    @GetMapping("/home")
    public String home(HttpServletRequest request){
        //获取登录的账户
        MyUserDetails userDetails = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取一级菜单
        List<Resources> topMenus = resourcesService.loadMenu(userDetails.getUsername(), Constant.MENU_TOP,null);
        request.setAttribute("realname",userDetails.getRealName());
        request.setAttribute("topMenus",topMenus);
        return "system/home/homePage";
    }

    /**
     * 获取二级菜单
     */
    @ResponseBody
    @GetMapping(value = "/secondMenu",produces = "text/plain; charset=utf-8")
    public String  secondMenu(HttpServletRequest request){
        //获取登录的账户
        MyUserDetails userDetails = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        String parentId = request.getParameter("parentId");
        //获取二级菜单
        List<Resources> secendMenus = resourcesService.loadMenu(username, Constant.MENU_SECOND,parentId);
        for(Resources secendMenu :secendMenus){
            List<Resources> thirdMenus = resourcesService.loadMenu(username, Constant.MENU_THIRD,secendMenu.getId());
            if(thirdMenus!=null && thirdMenus.size()>0){
                String childJson = CommonUtils.objectToJson(thirdMenus);
                secendMenu.setChildJson(childJson);
            }
        }
        return CommonUtils.objectToJson(secendMenus);
    }


    /**
     * 跳转到工作首页
     */
    @GetMapping("/main")
    public String mainPage(HttpServletRequest request){
        return "system/home/main";
    }
}
