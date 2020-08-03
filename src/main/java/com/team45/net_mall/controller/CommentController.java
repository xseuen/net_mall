package com.team45.net_mall.controller;

import com.team45.net_mall.common.domain.Comment;
import com.team45.net_mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/comment")
    @ResponseBody
    public String  updateComment(@RequestBody Comment comment){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       comment.setCreateTime(sdf.format(date));
        return commentService.update(comment)==0?"提交失败":"提交成功";
    }
}
