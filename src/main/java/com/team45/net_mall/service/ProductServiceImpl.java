package com.team45.net_mall.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.team45.net_mall.common.domain.Product;
import com.team45.net_mall.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> getList() {
        //页码  页面大小
        PageHelper.startPage(2, 10);//第1页 每页5条
        return productMapper.list();
    }
}
