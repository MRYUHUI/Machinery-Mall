package com.mall.mechmall.service.impl;

import com.mall.mechmall.domain.Order;
import com.mall.mechmall.mapper.OrderMapper;
import com.mall.mechmall.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderByOrderNo(Integer order_no) {
        return null;
    }

    @Override
    public Boolean checkOrderByOrderNo(Integer order_no) {
        return null;
    }

    @Override
    public Boolean checkOrderByUid(Integer uid) {
        return null;
    }

    @Override
    public Boolean checkOrderByAddrId(Integer addr_id) {
        return null;
    }

    @Override
    public Boolean insertOrder(Order order) {
        return null;
    }

    @Override
    public Object findOrderAmount(Integer order_no) {
        return null;
    }

    @Override
    public Boolean checkOrderAmount(Integer order_no, BigDecimal amount) {
        return null;
    }

    @Override
    public Boolean updateOrderType(Integer order_no, Integer type) {
        return null;
    }

    @Override
    public Integer findOrderFreight(Integer order_no) {
        return null;
    }

    @Override
    public Boolean updateOrderStatus(Integer order_no, Integer status) {
        return null;
    }

    @Override
    public List<Order> findAllOrders(int page, int size) {
        return null;
    }

    @Override
    public int countOrders() {
        return 0;
    }

    @Override
    public Boolean deleteOrder(Integer order_no) {
        return null;
    }

    @Override
    public Order findOrderById(Integer id) {
        return null;
    }

    @Override
    public Order findOrderByUid(Integer uid) {
        return null;
    }

    @Override
    public List<Order> searchOrders(String keyword, int page, int size) {
        return null;
    }

    @Override
    public int countOrdersByKeyword(String keyword) {
        return 0;
    }

    @Override
    public boolean updateOrderInfo(Order order) {
        return false;
    }
}
