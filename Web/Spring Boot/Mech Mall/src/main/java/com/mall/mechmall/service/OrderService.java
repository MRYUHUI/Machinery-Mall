package com.mall.mechmall.service;

import com.mall.mechmall.domain.Order;

import java.math.BigDecimal;
import java.util.List;

/**接口定义增删改查
 * @Author:ate
 * @Date: 2024-06-25-21:58
 * @Description:
 */
public interface OrderService {
        Order findOrderByOrderNo(Integer order_no);

        Boolean checkOrderByOrderNo(Integer order_no);

        Boolean checkOrderByUid(Integer uid);

        Boolean checkOrderByAddrId(Integer addr_id);

        Boolean insertOrder(Order order);

        Object findOrderAmount(Integer order_no);

        Boolean checkOrderAmount(Integer order_no, BigDecimal amount);

        Boolean updateOrderType(Integer order_no, Integer type);

        Integer findOrderFreight(Integer order_no);

        Boolean updateOrderStatus(Integer order_no, Integer status);

        List<Order> findAllOrders(int page, int size);

        int countOrders();
//-----------
      Boolean deleteOrder(Integer orderNo);




        Order findOrderById(Integer order_no);

        Order findOrderByUid(Integer uid);

        List<Order> searchOrders(String keyword, int page, int size);

        int countOrdersByKeyword(String keyword);

        boolean updateOrderInfo(Order order);



//        Boolean checkOrderByOrderNo(Integer order_no);
//        Boolean checkOrderByUid(Integer uid);
//        Boolean checkOrderByAddrId(Integer addr_id);
//        Boolean insertOrder(Order order);
//        BigDecimal findOrderAmount(Integer order_no);
//        Boolean checkOrderAmount(Integer order_no, BigDecimal amount);
//        Boolean updateOrderType(Integer order_no, Integer type);
//        Integer findOrderFeight(Integer order_no);
//        Boolean updateOrderStatus(Integer order_no, Integer status);
//        List<Order> findAllOrders(int page, int size);
//        int countOrders();
//        Boolean deleteOrder(Integer order_no);
//        Order findOrderById(Integer id);
//        Order findOrderByUid(Integer uid);
//        List<Order> searchOrders(String keyword, int page, int size);
//        int countOrdersByKeyword(String keyword);



////        public interface OrderService {
//              interface  Order findOrderByOrderNo(Integer order_no);
//                Boolean checkOrderByOrderNo(Integer order_no);
//                Boolean checkOrderByUid(Integer uid);
//                Boolean checkOrderByAddrId(Integer addr_id);
//                Boolean insertOrder(Order order);
//                BigDecimal findOrderAmount(Integer order_no);
//                Boolean checkOrderAmount(Integer order_no, BigDecimal amount);
//                Boolean updateOrderType(Integer order_no, Integer type);
//                Integer findOrderFeight(Integer order_no);
//                Boolean updateOrderStatus(Integer order_no, Integer status);
//                List<Order> findAllOrders(int page, int size);
//                int countOrders();
//                Boolean deleteOrder(Integer order_no);
//                Order findOrderById(Integer id);
//                Order findOrderByUid(Integer uid);
//                List<Order> searchOrders(String keyword, int page, int size);
//                int countOrdersByKeyword(String keyword);
////        }


}
