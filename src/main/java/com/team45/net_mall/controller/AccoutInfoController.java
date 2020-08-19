package com.team45.net_mall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.common.domain.Order;
import com.team45.net_mall.common.domain.Wallet;
import com.team45.net_mall.service.MemberService;
import com.team45.net_mall.service.OrderService;
import com.team45.net_mall.service.OrderServiceImpl;
import com.team45.net_mall.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;


@Controller
public class AccoutInfoController {
    @Autowired
    MemberService memberService;
    @Autowired
    WalletService walletService;
    @Autowired
    OrderServiceImpl orderService;

    /**
     * 获取订单和钱包信息
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/myaccount")
    public String showAccoutInfo(HttpSession session, Model model,
                                 @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "4") int pageSize){
        //判断是否登录
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null) {
                return "redirect:/";
            }
            //获取钱包，发送到前端
            Wallet wallet = walletService.getWalletByMid(loginUser.getId());
            model.addAttribute(wallet);
            //获取订单，发送到前端
            List<Order> orders = orderService.selectByUidPage(loginUser.getId(),pageNum,pageSize);
            PageInfo<Order> pageInfo=new PageInfo<>(orders);
            orders.sort(Comparator.comparingInt(Order::getId).reversed());
            model.addAttribute("orders",orders);
            model.addAttribute("pages",pageInfo);
        }
        return "front-end/myaccount";
    }

    /**
     * 用户更改信息
     * @param member
     * @return
     */
    @PostMapping("/userupdate")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean update(@RequestBody Member member) {
       int  i = memberService.update(member);
        return i==0?false:true;
    }

    /**
     * 用户更改密码
     * @param member
     * @return
     */
    @RequestMapping("/updatepassword")
    @ResponseBody
     public Boolean updateByNameAndPhone(@RequestBody Member member) {
       int i =  memberService.updateByNameAndPhone(member);
        return i==0?false:true;
    }

    /**
     * 钱包充值码
     * @param moneyCode
     * @return
     */
    @RequestMapping("/charge")
    @ResponseBody
    public String charge(@RequestBody MoneyCode moneyCode){
        return walletService.updateByUid(moneyCode.getId(),moneyCode.getMd5());
    }


}
