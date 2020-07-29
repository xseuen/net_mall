package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private MemberService memberService;

    @RequestMapping(value = "/tologin",method = RequestMethod.GET)
    public String tologin(){
        return "pages/examples/login.html";
    }

    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public String login(Member member, Model model, HttpSession session) {
        Member memberInDB = memberService.login(member);
        if (memberInDB == null) {
            model.addAttribute("member", member);
            model.addAttribute("msg", "用户名或密码错误");
            return "pages/examples/login.html";
        } else {
            //登录成功s
            session.setAttribute("loginUser",memberInDB);
            return "front-end/index.html";
        }
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "pages/examples/register.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();//清除登录信息
        return "pages/examples/login.html";
    }

    @GetMapping("/selectByName")
    @ResponseBody//表示不是返回页面
    //ajax请求不能跳转页面
    public Boolean selectByName(String name) {
        //true:表示不存在，可注册
        //false：表示已有，不能注册
        boolean flag = memberService.selectByName(name);
        return flag;
    }

    @PostMapping("/register")
    @ResponseBody
    public Boolean register(@RequestBody Member member) {
        int i =  memberService.insert(member);
        return i==0?false:true;
    }
}
