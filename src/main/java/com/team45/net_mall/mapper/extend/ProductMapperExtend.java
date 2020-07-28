package com.team45.net_mall.mapper.extend;

import com.team45.net_mall.common.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductMapperExtend {
    List<Product> list();
}
