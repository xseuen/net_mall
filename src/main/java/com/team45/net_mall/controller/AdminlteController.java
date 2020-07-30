package com.team45.net_mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminlteController {

    @RequestMapping("/adminlte")
    public String toAdminlte(){
        return "/product/list";
    }
}

