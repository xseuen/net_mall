package com.team45.net_mall.mapper;

import com.team45.net_mall.common.domain.CartItem;
import com.team45.net_mall.common.domain.CartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemMapper {
    long countByExample(CartItemExample example);

    int deleteByExample(CartItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    List<CartItem> selectByExample(CartItemExample example);

    CartItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CartItem record, @Param("example") CartItemExample example);

    int updateByExample(@Param("record") CartItem record, @Param("example") CartItemExample example);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);
}