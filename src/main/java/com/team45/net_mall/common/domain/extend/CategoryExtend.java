package com.team45.net_mall.common.domain.extend;

import com.team45.net_mall.common.domain.Product;

import java.util.List;

public class CategoryExtend {
    private List<Product> product;

    public List<Product> getProducts() {
        return product;
    }

    public void setProducts(List<Product> product) {
        this.product = product;
    }
}
