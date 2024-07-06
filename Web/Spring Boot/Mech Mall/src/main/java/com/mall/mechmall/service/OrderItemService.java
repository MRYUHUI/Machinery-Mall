package com.mall.mechmall.service;

import com.mall.mechmall.domain.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/7/4
 * @Description:
 */
public interface OrderItemService {
    List<OrderItem> findAllOrderItemsByUid(int uid);

    List<OrderItem> findOrderItemByOrderId(int orderId);

    Boolean addOrderItem(OrderItem orderItem);

    Boolean delOrderItem(int id);

    Boolean updateOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItem(int offset, int size);

    int countOrderItem();
}
