package com.mall.mechmall.service;

import com.mall.mechmall.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 接口定义增删改查
 *
 * @Author:ate
 * @Date: 2024-06-25-21:58
 * @Description:
 */
public interface OrderService {
    //thh增加
    List<Order> getOrderByUserId(int uid);

    Order findOrderByOrderNo(String order_no);

    Boolean checkOrderByOrderNo(String order_no);

    Boolean checkOrderByUid(Integer uid);

    Boolean checkOrderByAddrId(Integer addr_id);

    public boolean insertOrder(Order order);

    Object findOrderAmount(String order_no);

    Boolean checkOrderAmount(String order_no, BigDecimal amount);

    Boolean updateOrderType(String order_no, Integer type);

    Integer findOrderFreight(String order_no);

    Boolean updateOrderStatus(String order_no, Integer status);

    List<Order> findAllOrders(int page, int size);

    int countOrders();

    //-----------
    public  boolean deleteOrder(Integer orderId);


    Order findOrderById(int id);

    Order findOrderByUid(Integer uid);

    List<Order> searchOrders(String keyword, int page, int size);

    int countOrdersByKeyword(String keyword);

    boolean updateOrderInfo(Order order);

    public boolean updateByOrderNo(Order order);

}
