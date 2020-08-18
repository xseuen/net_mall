package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Cart;
import com.team45.net_mall.common.domain.CartItem;
import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = {"/queryCartData"})
    public String queryCartData(HttpSession session, Model model){
        Member member = (Member) session.getAttribute("loginUser");
        model.addAttribute("cartList",cartService.queryCartData(member));
        return "front-end/cart";
    }

    @GetMapping("/addCart")
    @ResponseBody
    public boolean addCart(int productId,HttpSession session){
        return cartService.addCart(productId,session)!=0;
    }

    @GetMapping("/delCart")
    @ResponseBody
    public boolean delCart(int cartId){
        return cartService.delCart(cartId)!=0;
    }

    @GetMapping("/editCart")
    @ResponseBody
    public boolean editCart(int cartItemId,int count){
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setNum(count);
        return cartService.editCart(cartItem)!=0;
    }
    @GetMapping(value = {"/queryCartDataAjax"})
    @ResponseBody
    public List queryCartDataAjax(HttpSession session){
        Member member = (Member) session.getAttribute("loginUser");
        return cartService.queryCartData(member);
    }

}
