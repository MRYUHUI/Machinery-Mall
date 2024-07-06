package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.*;
import com.mall.mechmall.service.OrderItemService;
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

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 管理员跟新用户信息
     * @param
     * @return Object
     */

    @PostMapping("/update")
    public Object updateOrder(@RequestBody Order order) {
        boolean updated = orderService.updateOrderInfo(order);
        if (updated) {
            // 获取更新后的订单信息
            Order newOrder = orderService.findOrderByOrderNo(order.getOrderNo());
            return (newOrder);
        } else {
            return getJson("信息修改失败", false);
        }
    }

//        @GetMapping("/deleteOrder")
//        public Object deleteOrder(@RequestParam("order_no") String orderNo) {
//            boolean deleted = orderService.deleteOrder(orderNo);
//            if (deleted) {
//                return getJson("订单删除成功", true);
//            } else {
//                return getJson("订单删除失败", false);
//            }
//        }
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
    @GetMapping("[表情]archOrders")
    public Object searchOrders(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        List<Order> searchResult = orderService.searchOrders(keyword, page, size);
        int total = orderService.countOrdersByKeyword(keyword);
        JSONObject json = getJson("搜索成功", true);
        json.put(DATA, searchResult);
        json.put("total", total);
        return json;
    }


    /**
     *+++++++++++++++++++++++++++
     */
    @GetMapping("/allOrderItem")
    public Object getAllOrderItem(@RequestParam int page, @RequestParam int size){
        List<OrderItem> allOrderItem = orderItemService.getAllOrderItem(page, size);
        System.out.println(allOrderItem);
        JSONObject json = getJson("获取成功", true);
        int total = orderItemService.countOrderItem();
        json.put(DATA, allOrderItem);
        json.put("total", total);
        return json;
    }

    @GetMapping("/getOrderById")
    public Object getOrderById(@RequestParam int id){
        Order order = orderService.findOrderById(id);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, order);
        return json;
    }

    @GetMapping("/getOrderItemById")
    public Object getOrderItemById(@RequestParam int id){
        System.out.println("===================\n" + id);
        List<OrderItem> orderItem =orderItemService.findOrderItemByOrderId(id);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, orderItem);
        return json;
    }

}