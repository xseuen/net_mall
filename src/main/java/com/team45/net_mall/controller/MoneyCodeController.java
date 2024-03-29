package com.team45.net_mall.controller;

import com.github.pagehelper.PageInfo;
import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.service.MoneyCodeService;
import com.team45.net_mall.service.MoneyCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;


@Controller
public class MoneyCodeController {
    @Autowired
    MoneyCodeServiceImpl moneyCodeService;
    /**
     * 获取充值码列表
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/code_list")
    public String list (Model model, HttpSession session,
                        @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null || loginUser.getType() < 1) {
                return "redirect:/";
            }
        }
        List<MoneyCode> list= moneyCodeService.list(pageNum,pageSize);
        PageInfo<MoneyCode> pageInfo=new PageInfo<>(list);
        model.addAttribute("pages",pageInfo);
        model.addAttribute("codes",list);
        return "after-end/moneycode/moneycode";
    }

    /**
     * 删除充值码
     * @param id
     * @return
     */
    @GetMapping("/code/deleteById")
    @ResponseBody
    public String deleteCode (@RequestParam("id") Integer id){
        return moneyCodeService.delete(id)==1?"删除成功":"删除失败";
    }


    /**
     * 生成充值码
     */
    @RequestMapping("/makeCode")
    @ResponseBody
    public String makeCode(@RequestParam Double money){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<9;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
      int i = moneyCodeService.insert(sb.toString(),money);

       return i==1?"生成成功":"生成失败";
    }

}
