package com.mall.mechmall.service.Impl;


import com.mall.mechmall.service.OrderService;
import com.mall.mechmall.domain.Order;
import com.mall.mechmall.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:
 * @Date: 2024-06-25-21:59，它通过OrderMapper与数据库进行交互。OrderServiceImpl类的每个方法都是对OrderMapper中对应方法的调用，具体来说是通过OrderMapper进行数据库操作。以下是每个方法的详细解释：
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    //thh增加
    @Override
    public List<Order> getOrderByUserId(int uid) {
        return orderMapper.getOrderByUserId(uid);
    }


    @Override
    public Order findOrderByOrderNo(String order_no) {
        return orderMapper.findOrderByOrderNo(order_no);
    }

    @Override
    public Boolean checkOrderByOrderNo(String order_no) {
        return orderMapper.checkOrderByOrderNo(order_no) > 0;
    }

    @Override
    public Boolean checkOrderByUid(Integer uid) {
        return orderMapper.checkOrderByUid(uid) > 0;
    }

    @Override
    public Boolean checkOrderByAddrId(Integer addr_id) {
        return orderMapper.checkOrderByAddrId(addr_id) > 0;
    }

    @Override
    public boolean insertOrder(Order order) {
        return orderMapper.insertOrder(order) > 0;
    }

    @Override
    public BigDecimal findOrderAmount(String order_no) {
        return orderMapper.findOrderAmount(order_no);
    }

    @Override
    public Boolean checkOrderAmount(String order_no, BigDecimal amount) {
        return orderMapper.checkOrderAmount(order_no, amount) > 0;
    }

    @Override
    public Boolean updateOrderType(String order_no, Integer type) {
        return orderMapper.updateOrderType(order_no, type) > 0;
    }

    @Override
    public Integer findOrderFreight(String order_no) {
        return null;
    }

    @Override
    public Boolean updateOrderStatus(String order_no, Integer status) {
        return orderMapper.updateOrderStatus(order_no, status) > 0;
    }

    @Override
    public List<Order> findAllOrders(int page, int size) {
        int offset = (page - 1) * size;
        return orderMapper.findAllOrders(offset, size);
    }

    @Override
    public int countOrders() {
        return orderMapper.countOrders();
    }

    @Transactional
    @Override
    public boolean deleteOrder(Integer orderIdo) {
        return orderMapper.deleteOrder(orderIdo) > 0;
    }

    @Override
    public Order findOrderById(int id) {
        return orderMapper.findOrderById(id);
    }

    @Override
    public Order findOrderByUid(Integer uid) {
        return orderMapper.findOrderByUid(uid);
    }

    @Override
    public List<Order> searchOrders(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return orderMapper.searchOrders(keyword, offset, size);
    }


    @Override
    public int countOrdersByKeyword(String keyword) {
        return orderMapper.countOrdersByKeyword(keyword);
    }

    @Override
    public boolean updateOrderInfo(Order order) {
        return false;
    }
}