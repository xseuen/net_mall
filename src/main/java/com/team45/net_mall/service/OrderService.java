package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Order;
import com.team45.net_mall.common.domain.OrderItem;

import java.util.List;

public interface OrderService {
    int insert(Order order);

    List<Order> selectByUid(Integer uid);

    int insertItem(OrderItem orderItem);

    Boolean update(Order order);


    List<OrderItem> selectAllByOid(Integer integer);

    Order selectById(Integer id);

    String delorder(Integer id);
}
