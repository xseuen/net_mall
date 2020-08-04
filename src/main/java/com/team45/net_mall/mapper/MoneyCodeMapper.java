package com.team45.net_mall.mapper;

import com.team45.net_mall.common.domain.MoneyCode;
import com.team45.net_mall.common.domain.MoneyCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyCodeMapper {
    long countByExample(MoneyCodeExample example);

    int deleteByExample(MoneyCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MoneyCode record);

    int insertSelective(MoneyCode record);

    List<MoneyCode> selectByExample(MoneyCodeExample example);

    MoneyCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MoneyCode record, @Param("example") MoneyCodeExample example);

    int updateByExample(@Param("record") MoneyCode record, @Param("example") MoneyCodeExample example);

    int updateByPrimaryKeySelective(MoneyCode record);

    int updateByPrimaryKey(MoneyCode record);
}