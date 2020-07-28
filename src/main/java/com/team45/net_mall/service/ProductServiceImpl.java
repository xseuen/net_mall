package com.team45.net_mall.service;


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
        PageHelper.startPage(1, 10);//第1页 每页5条
        return productMapper.list();
    }

    @Override
    public Product selectByid(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }
    @Override
    public int update(Product product) {
        return  productMapper.updateByPrimaryKey(product);
    }

    @Override
    public int deleteById(Integer id) {
        //设置deleteStatus为1
        return productMapper.deleteByPrimaryKey(id);
    }

}
