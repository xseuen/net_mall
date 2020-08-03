package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



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
            return "forward:/";
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
        member.setStatus(1);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        member.setCreateTime(sdf.format(date));
        int i =  memberService.insert(member);
        return i==0?false:true;
    }

    @GetMapping("/user_list")
    public String list(Model model,HttpSession session) {
        //判断是否登录和权限
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null || loginUser.getType() < 2) {
                return "redirect:/";
            }
        }
        //调用服务层的获取数据的方法
        List<Member> list = memberService.getList();
        model.addAttribute("users", list);
        return "after-end/user/user-list";
    }

    @RequestMapping("/user/selectByid")
    // @ResponseBody//不跳转页面了，直接将数据返回给当前页面
    public String selectByid(Integer id, Model model) {
        //调用服务层的获取数据的方法
        Member member = memberService.selectByid(id);
        model.addAttribute("user", member);
        return "after-end/user/user-edit";
    }

    @GetMapping("/user/deleteById")
    public String deleteById(@RequestParam("id") Integer id) {
        Member member = memberService.selectByid(id);
        member.setStatus(0);
        memberService.update(member);
        return "redirect:/user_list";
    }

    @RequestMapping("/forgot")
    public String forgotPage(){
        return "/front-end/forgot";
    }


}
