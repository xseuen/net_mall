package com.team45.net_mall.mapper;

import com.team45.net_mall.common.domain.Categoty;
import com.team45.net_mall.common.domain.CategotyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategotyMapper {
    long countByExample(CategotyExample example);

    int deleteByExample(CategotyExample example);

    int deleteByPrimaryKey(String cid);

    int insert(Categoty record);

    int insertSelective(Categoty record);

    List<Categoty> selectByExample(CategotyExample example);

    Categoty selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") Categoty record, @Param("example") CategotyExample example);

    int updateByExample(@Param("record") Categoty record, @Param("example") CategotyExample example);

    int updateByPrimaryKeySelective(Categoty record);

    int updateByPrimaryKey(Categoty record);
}