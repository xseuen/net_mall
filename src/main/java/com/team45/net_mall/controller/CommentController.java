package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Comment;
import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 前台提交留言反馈
     * @param comment
     * @return
     */
    @RequestMapping("/comment")
    @ResponseBody
    public String  updateComment(@RequestBody Comment comment){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       comment.setCreateTime(sdf.format(date));
        return commentService.update(comment)==0?"提交失败":"提交成功";
    }

    /**
     * 后台留言管理
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/comment/commentlist")
    public String list (Model model, HttpSession session){
        if (session == null) {
            return "redirect:/";
        } else {
            Member loginUser = (Member) session.getAttribute("loginUser");
            if (loginUser == null || loginUser.getType() < 1) {
                return "redirect:/";
            }
        }
        List<Comment> list= commentService.list();
        model.addAttribute("comments",list);
        return "after-end/comment/comment-list";
    }

    /**
     * 删除留言
     * @param id
     * @return
     */
    @GetMapping("/comment/deleteById")
    @ResponseBody
    public String deleteComment (@RequestParam("id") Integer id){
        return commentService.delete(id)==1?"删除成功":"删除失败";
    }
    @RequestMapping("/comment/selectById")
    public String selectById( Integer id,Model model){
        Comment comment=commentService.select(id);
        model.addAttribute("lists",comment);
        return "after-end/comment/comment-view";
    }
}
