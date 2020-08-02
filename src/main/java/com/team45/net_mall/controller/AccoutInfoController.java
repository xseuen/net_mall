package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.Wallet;
import com.team45.net_mall.service.MemberService;
import com.team45.net_mall.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AccoutInfoController {
    @Autowired
    MemberService memberService;
    @Autowired
    WalletService walletService;
    @RequestMapping("/myaccount")
    public String showAccoutInfo(HttpSession session, Model model){
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
        }
        return "front-end/myaccount";
    }
    @PostMapping("/userupdate")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean update(@RequestBody Member member) {
       int  i = memberService.update(member);
        return i==0?false:true;
    }
    @RequestMapping("/updatepassword")
    @ResponseBody
     public Boolean updateByNameAndPhone(@RequestBody Member member) {
       int i =  memberService.updateByNameAndPhone(member);
        return i==0?false:true;
    }
}
