package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class MemberController {
    @Autowired
    private MemberServiceImpl memberService;

    @RequestMapping(value = "/tologin",method = RequestMethod.GET)
    public String tologin(){
        return "front-end/login";
    }

    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public String login(Member member, Model model, HttpSession session) {
        Member memberInDB = memberService.login(member);
        if (memberInDB == null) {
            model.addAttribute("member", member);
            model.addAttribute("msg", "用户名或密码错误");
            return "front-end/login";
        } else {
            //登录成功
            session.setAttribute("loginUser",memberInDB);
            return "front-end/index";
        }
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "front-end/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();//清除登录信息
        return "forward:/";
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
        member.setType(0);
        member.setStaus(1);
        member.setCreateTime(new Date());
        int i =  memberService.insert(member);
        return i==0?false:true;
    }

}
