package com.team45.net_mall.service;

import com.github.pagehelper.PageHelper;
import com.team45.net_mall.common.domain.*;
import com.team45.net_mall.mapper.OrderItemMapper;
import com.team45.net_mall.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public List<Order> selectByUid(Integer uid) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andMemberIdEqualTo(uid);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        return orders;
    }

    @Override
    public List<Order> selectByUidPage(Integer uid, int pageNum, int pageSize) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andMemberIdEqualTo(uid);
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        return orders;
    }

    @Override
    public int insertItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    @Override
    public Boolean update(Order order) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(order.getId());
        return orderMapper.updateByExampleSelective(order,orderExample)<1?false:true;
    }

    @Override
    public List<OrderItem> selectAllByOid(Integer id) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrderIdEqualTo(id);
        return orderItemMapper.selectByExample(orderItemExample);
    }

    @Override
    public Order selectById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public String delorder(Integer id) {
        int i = orderMapper.deleteByPrimaryKey(id);
        return i<1?"删除失败":"删除成功";
    }
}
