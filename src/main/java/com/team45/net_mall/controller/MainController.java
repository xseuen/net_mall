package com.team45.net_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {


    @RequestMapping("/")
    public String showAll(){
        return "/front-end/index.html";
    }

   //这是dev的一个测试
}
