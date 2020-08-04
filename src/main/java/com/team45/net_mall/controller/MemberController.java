package com.team45.net_mall.controller;


import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.MemberExample;
import com.team45.net_mall.common.domain.Wallet;
import com.team45.net_mall.service.MemberServiceImpl;
import com.team45.net_mall.service.WalletService;
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
    @Autowired
    WalletService walletService;

    @RequestMapping(value = "/tologin",method = RequestMethod.GET)
    public String tologin(){
        return "front-end/login";
    }

    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public String login(Member member, Model model, HttpSession session) {
        Member memberInDB = memberService.login(member);
        if(!member.getAvatar().isEmpty()&&memberInDB!=null) {
            memberInDB.setAvatar(member.getAvatar());
             memberService.update(memberInDB);
        }
        if (memberInDB == null) {
            model.addAttribute("member", member);
            model.addAttribute("msg", "用户名或密码错误");
            return "front-end/login";
        } else if(memberInDB.getStatus()==0){
            model.addAttribute("member", member);
            model.addAttribute("msg", "该账号已被冻结，请联系管理员");
            return "front-end/login";
        }
        //登录成功
        session.setAttribute("loginUser",memberInDB);
        return "forward:/";
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

    /**
     * 注册查重
     * @param name
     * @return
     */
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
        member.setIsDeleted(0);
        member.setAvatar( "dist/img/user1-128x128.jpg");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        member.setCreateTime(sdf.format(date));
        int i =  memberService.insert(member);
        if(i!=0){
            Wallet wallet =new Wallet();
            wallet.setBalance(0.0);
            wallet.setLevel(0L);
            wallet.setMenberNickname(member.getNickName());
            wallet.setMemberId(memberService.login(member).getId());
            wallet.setUpdateTime(sdf.format(date));
            walletService.insertWallet(wallet);
            return true;
        }
        return false;
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
    public String selectByid(Integer id, Model model) {
        //调用服务层的获取数据的方法
        Member member = memberService.selectByid(id);
        model.addAttribute("user", member);
        return "after-end/user/user-edit";
    }

    @GetMapping("/user/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("id") Integer id) {
        Member member = memberService.selectByid(id);
        member.setIsDeleted(1);
        member.setStatus(0);
        memberService.update(member);
        return memberService.update(member)==1?"删除成功":"删除失败";
    }

    /**
     *  管理员删除用户验证
     * @param
     * @return
     */
    @RequestMapping("/idCommit")
    @ResponseBody
    public String commitByid(@RequestBody Member member){
        Member member1 = memberService.selectByid(member.getId());
        return member.getPassword().equals(member1.getPassword())?"密码正确":"密码错误";

    }

    /**
     *  用户找回密码
     * @return
     */
    @RequestMapping("/forgot")
    public String forgotPage(){
        return "/front-end/forgot";
    }

    /**
     * 自动获取头像
     * @param username
     * @return
     */
    @GetMapping("/getAvatar")
    @ResponseBody
    public String getAvatarByName(@RequestParam String username){
        return memberService.getAvatarByName(username);
    }

}
