package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.ProductWithBLOBs;

import java.util.List;

public interface ProductService {

    List<ProductWithBLOBs> getList();

    List<ProductWithBLOBs>  getListByCategory(String cid);

    ProductWithBLOBs selectByid(Integer id);

    int update(ProductWithBLOBs productWithBLOBs);

    int add(ProductWithBLOBs productWithBLOBs);
}
