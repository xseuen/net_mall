package com.team45.net_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {


    @RequestMapping("/")
    public String showAll(){
        return "/front-end/index.html";
    }
     //这是dev1开发线的测试
    @GetMapping("/index")
    public String toAdminlte(){
        return "/index.html";
    }

}
