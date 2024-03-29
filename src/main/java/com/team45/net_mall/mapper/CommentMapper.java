package com.team45.net_mall.mapper;

import com.team45.net_mall.common.domain.Comment;
import com.team45.net_mall.common.domain.CommentExample;
import java.util.List;

import com.team45.net_mall.mapper.extend.CommentMapperExtend;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper extends CommentMapperExtend {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);


}