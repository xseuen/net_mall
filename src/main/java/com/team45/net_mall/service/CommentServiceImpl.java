package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Comment;
import com.team45.net_mall.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public int update(Comment comment) {
        return commentMapper.insert(comment);
    }
}
