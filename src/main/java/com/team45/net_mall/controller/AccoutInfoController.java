package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.ProductWithBLOBs;
import com.team45.net_mall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller

public class AccoutInfoController {
    @Autowired
    MemberService memberService;
    @RequestMapping("/myaccount")
    public String showAccoutInfo(HttpSession session){
        //判断是否登录
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null) {
                return "redirect:/";
            }
        }
        return "front-end/myaccount";
    }
    @PostMapping("/userupdate")
    @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public Boolean update(@RequestBody Member member) {

       int  i = memberService.update(member);

        return i==0?false:true;
    }
}
