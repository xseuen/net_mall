package com.team45.net_mall.controller;

import com.github.pagehelper.PageInfo;
import com.team45.net_mall.common.domain.Category;
import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.ProductWithBLOBs;
import com.team45.net_mall.service.CategoryService;
import com.team45.net_mall.service.ProductService;
import com.team45.net_mall.service.ProductServiceImpl;
import com.team45.net_mall.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    UploadService uploadService;

    /**
     * 后台产品列表
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/list")
    public String list(Model model,HttpSession session,
                       @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "5") int pageSize) {
        //判断是否登录和权限
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null || loginUser.getType() < 1) {
                return "redirect:/";
            }
        }
        //调用服务层的获取数据的方法
        List<ProductWithBLOBs> list = productService.getAllList(pageNum,pageSize);
        PageInfo<ProductWithBLOBs> pageInfo=new PageInfo<>(list);
        model.addAttribute("pages",pageInfo);
        model.addAttribute("pros", list);
        return "after-end/product/product-list";
    }

    /**
     * 后台产品添加
     * @param model
     * @return
     */
    @RequestMapping("/productadd")
    public String selectAllCategory(Model model) {
        //获取所有类别
        List<Category> list = categoryService.list();
        model.addAttribute("categoryList", list);
        return "after-end/product/product-add";
    }

    /**
     * 后台添加删除过产品更新状态
     * @param productWithBLOBs
     * @return
     */
    @PostMapping("/add")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean add(@RequestBody ProductWithBLOBs productWithBLOBs) {
        int i = 0;
        if (productWithBLOBs.getDeleteStatus() == null) {
            productWithBLOBs.setDeleteStatus(0);
            i = productService.add(productWithBLOBs);
        }
        return i == 0 ? false : true;
    }

    /**
     * 后台产品修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/selectByid")
    public String selectByid(Integer id, Model model) {
        //调用服务层的获取数据的方法
        ProductWithBLOBs productWithBLOBs = productService.selectByid(id);
        //获取所有类别
        List<Category> list = categoryService.list();
        model.addAttribute("categoryList", list);
        model.addAttribute("pro", productWithBLOBs);
        return "after-end/product/product-edit";
    }

    /**
     * 后台更新产品状态
     * @param productWithBLOBs
     * @return
     */
    @PostMapping("/update")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean update(@RequestBody ProductWithBLOBs productWithBLOBs) {
        int i = 0;
        if (productWithBLOBs.getDeleteStatus() == null) {
            productWithBLOBs.setDeleteStatus(0);
            i = productService.update(productWithBLOBs);
        }
        return i == 0 ? false : true;
    }

    /**
     * 后台产品删除
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("id") Integer id) {
        ProductWithBLOBs productWithBLOBs = productService.selectByid(id);
        productWithBLOBs.setDeleteStatus(1);
        productService.update(productWithBLOBs);
        return "forward:/product/list";
    }

    /**
     * 后台产品上传图片
     * @param id
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String ,Object> upload(@RequestParam Integer id, MultipartFile file){

        Map map = new HashMap<String,Object>();
        String s = uploadService.logUpload(file, id);
        map.put("msg",s);
        return map;
    }
}

