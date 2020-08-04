package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Category;
import com.team45.net_mall.common.domain.CategoryExample;
import com.team45.net_mall.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> list() {
        CategoryExample categoryExample =new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }
}
