package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Comment;
import com.team45.net_mall.common.domain.CommentExample;
import com.team45.net_mall.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public int update(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public List<Comment> list() {
        return commentMapper.list();
    }

    @Override
    public int delete(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Comment select(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
