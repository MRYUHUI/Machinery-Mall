package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.ShippingAddress;
import com.mall.mechmall.service.ShippingAddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;

/**
 * @Author: thh
 * @Date: 2024/7/3
 * @Description:
 */

@RestController
@RequestMapping("/user/address")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;


    @GetMapping("/allAddress")
    public Object getAddress(@RequestParam int userId){
        List<ShippingAddress> allAddresses = shippingAddressService.getShippingAddressMapper(userId);
        JSONObject json = getJson("获取成功", true);
        int total = shippingAddressService.countAddress(userId);
        json.put(DATA, allAddresses);
        json.put("total", total);
        return json ;
    }
    @PostMapping("/updateAddress")
    public Object updateAdress(@RequestBody ShippingAddress shippingAddress){
        if(shippingAddressService.updateShippingAddress(shippingAddress)){
            return getJson("地址修改成功",true);
        }else {
            return getJson("地址修改失败",false);
        }
    }
    @PostMapping("/addAddress")
    public Object addAddress(@RequestBody ShippingAddress shippingAddress){
        if(shippingAddressService.addShippingAddress(shippingAddress)){
            return getJson("地址增加成功",true);
        }else {
            return getJson("地址增加失败",false);
        }
    }

    @GetMapping("/deleteAddress")
    public Object deleteAddress(@RequestParam Integer id){
        if(shippingAddressService.deleteShippingAddress(id)){
            return getJson("地址删除成功",true);
        }else {
            return getJson("地址删除失败",false);
        }
    }
    @GetMapping("/updateDfault")
    public Object updateDfault(@RequestParam Integer id,@RequestParam Integer userId){
            shippingAddressService.resetDfault(userId);
            if(shippingAddressService.updateDfault(id)){
                return getJson("修改成功",true);
            }else {
                return getJson("修改失败",false);
            }
    }
    
}
