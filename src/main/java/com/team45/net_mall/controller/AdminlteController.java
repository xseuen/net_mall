package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminlteController {
    /**
     * 后台登陆权限
     * @param session
     * @return
     */
    @RequestMapping("/adminlte")
    public String toAdminlte(HttpSession session){
        //判断是否登录和权限
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null || loginUser.getType() < 1) {
                return "redirect:/";
            }
        }
        return "/after-end/index";
    }
}

