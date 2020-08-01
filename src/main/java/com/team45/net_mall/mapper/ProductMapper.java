package com.team45.net_mall.mapper;

import com.team45.net_mall.common.domain.Product;
import com.team45.net_mall.common.domain.ProductExample;
import com.team45.net_mall.common.domain.ProductWithBLOBs;
import java.util.List;

import com.team45.net_mall.mapper.extend.ProductMapperExtend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends ProductMapperExtend {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductWithBLOBs record);

    int insertSelective(ProductWithBLOBs record);

    List<ProductWithBLOBs> selectByExampleWithBLOBs(ProductExample example);

   List<Product> selectByExample(ProductExample example);

    ProductWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(ProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    int updateByPrimaryKey(Product record);
}