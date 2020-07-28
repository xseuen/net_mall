package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getList();

    Product selectByid(Integer id);

    int update(Product product);

    int deleteById(Integer id);
}
