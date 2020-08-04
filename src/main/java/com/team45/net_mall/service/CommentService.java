package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Comment;

import java.util.List;

public interface CommentService {
    int update(Comment comment);
    List<Comment> list();
    int delete(Integer id);
    Comment select(Integer id);
}
