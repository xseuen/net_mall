package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Member;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {


    @RequestMapping("/")
    public String showAll(Member member, HttpSession session){
        session.setAttribute("loginUser",member);
        return "/front-end/index";
    }


}
