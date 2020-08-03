package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.ProductWithBLOBs;
import com.team45.net_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        List<ProductWithBLOBs>  list3=productService.getList();
        model.addAttribute("pro1",list);
        model.addAttribute("pro2",list1);
        model.addAttribute("pro3",list2);
        model.addAttribute("pro4",list3);
        return "front-end/index";
    }

    @RequestMapping("/list-view")
    public String showList(Model model){
        List<ProductWithBLOBs>  list4=productService.getList();
        List<ProductWithBLOBs>  list5=productService.getList();
        model.addAttribute("pro5",list4);
        model.addAttribute("pro6",list5);
        return "front-end/list-view";
    }

    @RequestMapping("/about-us")
    public String aboutUs(Model model){
        return "front-end/about-us";
    }
    @RequestMapping("/cart")
    public String cart(Model model){
        return "front-end/cart";
    }
    @RequestMapping("/contact-us")
    public String contactUs(Model model){
        return "front-end/contact-us";
    }
    @RequestMapping("/product-info")
    public String productInfo(@RequestParam("id") Integer id, Model model){
        if(id==null){
            return "forward:/";
        }
        ProductWithBLOBs productWithBLOBs = productService.selectByid(id);
        model.addAttribute("pro",productWithBLOBs);
        return "front-end/simple-product";
    }
}
