package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Category;
import com.team45.net_mall.common.domain.Product;
import com.team45.net_mall.common.domain.ProductWithBLOBs;
import com.team45.net_mall.service.CategoryService;
import com.team45.net_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/list")
    // @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public String list(Model model) {
        //调用服务层的获取数据的方法
        List<Product> list = productService.getList();
        model.addAttribute("pros", list);
        return "product/product-list";
    }

    @RequestMapping("/productadd")
    // @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public String selectAllCategory(Model model) {
        //获取所有类别
        List<Category> list = categoryService.list();
        model.addAttribute("categoryList", list);
        return "product/product-add";
    }
    @PostMapping("/add")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean add(@RequestBody ProductWithBLOBs productWithBLOBs) {
        int i=0;
        if(productWithBLOBs.getDeleteStatus()==null){
            productWithBLOBs.setDeleteStatus(0);
            i = productService.add(productWithBLOBs);
        }
        return i==0?false:true;
    }

    @RequestMapping("/selectByid")
    // @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public String selectByid(Integer id, Model model) {
        //调用服务层的获取数据的方法
        Product product = productService.selectByid(id);
        //获取所有类别
        List<Category> list = categoryService.list();
        model.addAttribute("categoryList",list);
        model.addAttribute("pro", product);
        return "product/product-edit";
    }
    @PostMapping("/update")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean update(@RequestBody Product product) {
        int i=0;
        if(product.getDeleteStatus()==null){
            product.setDeleteStatus(0);
            i = productService.update(product);
        }
        return i==0?false:true;
    }
    @GetMapping("/deleteById")
    //@ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public String deleteById(@RequestParam("id") Integer id) {
        Product product = productService.selectByid(id);
        product.setDeleteStatus(1);
        productService.update(product);
        //int i=  productService.deleteById(id);
        return "forward:/product/list";
    }

}

