package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Order;
import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.OrderService;
import com.mall.mechmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSONObject;
        import com.mall.mechmall.domain.User;
        import com.mall.mechmall.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import static com.mall.mechmall.utils.Consts.*;
        import java.util.List;
        import java.util.Map;

/**
 * @Author: yuhui
 * @Date: 2024-06-26-13:54
 * @Description: 管理员的用户管理模块
 */

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 管理员跟新用户信息
     * @param order
     * @return Object
     */
    @PostMapping("/update")
    public Object updateOrder(@RequestBody Order order) {
        boolean updated = orderService.updateOrderInfo(order);
        if (updated) {
            // 获取更新后的订单信息
            Order newOrder = orderService.findOrderByOrderNo(order.getOrderNo());
            return (newOrder);
//            JSONObject json = getJson("信息修改成功", true);
//            json.put(DATA, newOrder);
//            return json;
        } else {
            return getJson("信息修改失败", false);
        }
    }
//    @PostMapping("/update")
//    public ResponseEntity<Object> updateOrder(@RequestBody Order updatedOrder) {
//        // 假设 updatedOrder 包含了前端发送过来的修改后的订单信息
//        boolean success = orderService.updateOrder(updatedOrder);
//        if (success) {
//            Order savedOrder = orderService.findOrderByOrderNo(updatedOrder.getOrderNo());
//            return ResponseEntity.ok(savedOrder); // 返回修改后的订单信息
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("订单更新失败");
//        }
//    }
//--------------------------------------------
//    @GetMapping("/deleteOrder")
//    public Object deleteOrder(@RequestParam("order_no") Integer orderNo) {
//        boolean deleted = orderService.deleteOrder(orderNo);
//        if (deleted) {
//            return getJson("订单删除成功", true);
//        } else {
//            return getJson("订单删除失败", false);
//        }
//    }

//    @RestController
//    @RequestMapping("/admin/order")
//    public class AdminOrderController {
        @GetMapping("/deleteOrder")
        public Object deleteOrder(@RequestParam("order_no") Integer orderNo) {
            boolean deleted = orderService.deleteOrder(orderNo);
            if (deleted) {
                return getJson("订单删除成功", true);
            } else {
                return getJson("订单删除失败", false);
            }
        }

























    /**
     * 获取所有用户信息
     * @return Object
     */
    @GetMapping("/allOrders")
    public JSONObject findAllOrders(@RequestParam int page, @RequestParam int size) {
        List<Order> allOrders = orderService.findAllOrders(page, size);
        System.out.println(allOrders);
        int total = orderService.countOrders();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allOrders);
        json.put("total", total);
        System.out.println(json);
        return json;
    }
    /**
     * 搜索用户信息
     * @param keyword 搜索关键词（用户名或姓名）
     * @param page 当前页码
     * @param size 每页大小
     * @return 符合搜索条件的用户信息和总数的 JSON 对象
     */
    @GetMapping("/searchOrders")
    public Object searchOrders(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        List<Order> searchResult = orderService.searchOrders(keyword, page, size);
        int total = orderService.countOrdersByKeyword(keyword);
        JSONObject json = getJson("搜索成功", true);
        json.put(DATA, searchResult);
        json.put("total", total);
        return json;
    }

}


