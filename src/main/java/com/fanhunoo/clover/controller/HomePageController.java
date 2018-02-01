package com.fanhunoo.clover.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页相关
 */
@Controller
public class HomePageController {

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
    public String home(){
        return "system/home/homePage";
    }
}
