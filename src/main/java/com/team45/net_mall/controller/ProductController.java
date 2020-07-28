package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Product;
import com.team45.net_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/adminlte")
    // @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public String list(Model model) {
        //调用服务层的获取数据的方法
        List<Product> list = productService.getList();
        model.addAttribute("pros", list);
        return "index";
    }

}

