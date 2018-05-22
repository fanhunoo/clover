package com.fanhunoo.clover.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面跳转
 */
@Controller
public class ErrorController {

    @GetMapping("/error/{code}")
    public String errorReturn(@PathVariable("code") String code){
        return "/error/"+code;
    }
}
