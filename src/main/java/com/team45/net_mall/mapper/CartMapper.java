package com.team45.net_mall.mapper;

import com.team45.net_mall.common.domain.Cart;
import com.team45.net_mall.common.domain.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}