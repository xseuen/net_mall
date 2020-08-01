package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.ProductWithBLOBs;
import com.team45.net_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@Controller
public class MainController {
    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String showAll(Model model){
        List<ProductWithBLOBs> list = productService.getListByCategory("c001");
        List<ProductWithBLOBs> list1 = productService.getListByCategory("c002");
        List<ProductWithBLOBs> list2 = productService.getListByCategory("c004");
        model.addAttribute("pro",list);
        model.addAttribute("pro1",list1);
        model.addAttribute("pro2",list2);
        return "front-end/index";
    }
}
