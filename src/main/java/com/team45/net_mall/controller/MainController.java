package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.*;
import com.team45.net_mall.service.CartService;
import com.team45.net_mall.service.CategoryService;
import com.team45.net_mall.service.OrderService;
import com.team45.net_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;

    /**
     * 主页
     * @param model
     * @return
     */
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
        List<ProductWithBLOBs> list1 = productService.getList();
        List<ProductWithBLOBs> list2 = productService.getListByCategory("c004");
        List<Category> list= categoryService.list();
        model.addAttribute("category",list);
        model.addAttribute("pro5",list1);
        model.addAttribute("pro6",list2);
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
    @RequestMapping("/orderinfo")
    public String orderInfo(HttpSession session,Model model,Integer... id){
        Order order= new Order();
        OrderItem orderItem = new OrderItem();
        Member member = (Member) session.getAttribute("loginUser");
        if(id==null){
            List<CartItem> list = cartService.queryCartData(member);
            model.addAttribute("Items",list);
            double payAmount=0;
            for (CartItem cartItem :
                    list) {
                payAmount += cartItem.getTotal();
            }
            order.setStatus(0);
            order.setPayAmount(payAmount);
            order.setMemberId(member.getId());
            order.setMemberNickname(member.getNickName());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setCreateTime(sdf.format(date));
            orderService.insert(order);
            List<Order> orders  = orderService.selectByUid(member.getId());
            order = orders.get(orders.size()-1);
            for (CartItem cartItem: list) {
                orderItem.setOrderId(orders.get(orders.size()-1).getId());
                orderItem.setProId(cartItem.getProId());
                orderItem.setProName(cartItem.getProName());
                orderItem.setProPrice(cartItem.getProPrice());
                orderItem.setNum(cartItem.getNum());
                orderItem.setTotal(cartItem.getTotal());
                orderService.insertItem(orderItem);
            }

        }else {
            List<OrderItem> list = orderService.selectAllByOid(id[0]);
            model.addAttribute("Items",list);
            order=orderService.selectById(id[0]);
        }
        model.addAttribute("order",order);
        return "front-end/orderinfo";
    }

}
