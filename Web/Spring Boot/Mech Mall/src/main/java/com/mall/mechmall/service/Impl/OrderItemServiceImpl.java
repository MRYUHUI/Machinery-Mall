package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.OrderItem;
import com.mall.mechmall.mapper.OrderItemMapper;
import com.mall.mechmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/7/4
 * @Description:
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> findAllOrderItemsByUid(int uid) {
        return orderItemMapper.findAllOrderItemsByUid(uid);
    }

    @Override
    public List<OrderItem> findOrderItemByOrderId(int orderId) {
        return orderItemMapper.findOrderItemByOrderId(orderId);
    }

    @Override
    public Boolean addOrderItem(OrderItem orderItem) {
        return orderItemMapper.insertOrderItem(orderItem) > 0;
    }

    @Override
    public Boolean delOrderItem(int id) {
        return orderItemMapper.deleteOrderItemById(id) > 0;
    }

    @Override
    public Boolean updateOrderItem(OrderItem orderItem) {
        return orderItemMapper.updateOrderItem(orderItem) > 0;
    }

    @Override
    public List<OrderItem> getAllOrderItem(int page, int size) {
        int offset = (page - 1) * size;
        return orderItemMapper.getAllOrderItem(offset, size);
    }

    @Override
    public int countOrderItem() {
        return orderItemMapper.countOrderItem();
    }

    @Override
    public boolean countOrderItemByUserId(int userId) {
        return orderItemMapper.countOrderItemByUserId(userId) > 0;
    }

}
