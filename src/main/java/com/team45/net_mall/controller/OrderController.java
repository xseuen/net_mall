package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.*;
import com.team45.net_mall.service.CartService;
import com.team45.net_mall.service.OrderService;
import com.team45.net_mall.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private CartService cartService;
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(@RequestBody Order order, HttpSession session){
        String result;
        Member member =(Member)session.getAttribute("loginUser");
        Wallet wallet =walletService.getWalletByMid(member.getId());
        if(wallet.getBalance()<order.getPayAmount()){
            return "您的余额不足";
        }else {
           wallet.setBalance(wallet.getBalance()-order.getPayAmount());
           result = walletService.updateById(wallet);
        }
        if (result=="支付成功"){
            order.setStatus(1);
            orderService.update(order);
            Cart cart = cartService.selectByUid(member.getId());
            List<CartItem> cartItems = cartService.queryCartData(member);
            for (CartItem cartItem :
                    cartItems) {
                cartService.delCart(cartItem.getId());
            }
            cartService.deleteById(cart.getId());
        }

        return result;
    }
    @RequestMapping("/delorder")
    @ResponseBody
    public String delorder(Integer id){
        return orderService.delorder(id);
    }
}
