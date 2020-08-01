package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.ProductWithBLOBs;

import java.util.List;

public interface ProductService {

    List<ProductWithBLOBs> getList();

    ProductWithBLOBs selectByid(Integer id);

    int update(ProductWithBLOBs productWithBLOBs);

    int deleteById(Integer id);

    int add(ProductWithBLOBs productWithBLOBs);
}
