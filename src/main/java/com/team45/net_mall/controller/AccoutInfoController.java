package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.common.domain.Wallet;
import com.team45.net_mall.service.MemberService;
import com.team45.net_mall.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AccoutInfoController {
    @Autowired
    MemberService memberService;
    @Autowired
    WalletService walletService;

    /**
     * 获取钱包信息
     * @param session
     * @param model
     * @return
     */
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
