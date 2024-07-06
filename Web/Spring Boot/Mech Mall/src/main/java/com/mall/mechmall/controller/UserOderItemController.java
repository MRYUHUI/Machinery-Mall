package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Order;
import com.mall.mechmall.domain.OrderItem;
import com.mall.mechmall.service.Impl.OrderItemServiceImpl;
import com.mall.mechmall.service.Impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;

/**
 * @Author: thh
 * @Date: 2024/7/4
 * @Description:
 */
@RestController
@RequestMapping("/user/orderitem")
public class UserOderItemController {

    @Autowired
    private OrderItemServiceImpl orderItemService;
    @Autowired
    private OrderServiceImpl orderService;



    @GetMapping("/allOrderItem")
    public Object getAddress(@RequestParam int userId){
        List<OrderItem> allOrderItem = orderItemService.findAllOrderItemsByUid(userId);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allOrderItem);
        return json ;
    }

    @GetMapping("/getOrderByUserId")
    public Object getOrderByUserId(@RequestParam int userId){
        List<Order> orders = orderService.getOrderByUserId(userId);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, orders);
        return json ;
    }
    @PostMapping("/addOrderItem")
    public Object addOrderItem(@RequestBody OrderItem orderItem){
        if(orderItemService.addOrderItem(orderItem)){
            return getJson("增加成功",true);
        }else {
            return getJson("增加失败",false);
        }
    }

    @PostMapping("/addOrder")
    public Object addOrder(@RequestBody Order order){

        if(orderService.insertOrder(order)){
            JSONObject json = getJson("增加成功", true);
            json.put(DATA, order.getId());
            return json;
        }else {
            return getJson("增加失败",false);
        }
    }

    @GetMapping("/delete/order/orderItem")
    public Object deleteOrderAndOrderItem(@RequestParam int orderItemId,int orderId){
        if(!orderItemService.delOrderItem(orderItemId))
            return getJson("订单项删除失败",false);
        if(!orderService.deleteOrder(orderId))
            return getJson("订单删除失败",false);
        return getJson("删除成功",true);
    }
}
