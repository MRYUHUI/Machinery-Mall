package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.mall.mechmall.utils.Consts.*;

import java.util.List;

/**
 * @Author:
 * @Date: 2024-07-02-21:49
 * @Description:
 */
@RestController
@RequestMapping("/commodity/home")
public class HomeCommodity {
    @Autowired
    private ProductService productService;

    /**
     * 获取所有热销商品
     * @return JSONObject
     */
    @GetMapping("/hot/all")
    public JSONObject findAllHotCommodity(){
        List<Product> hotProducts = productService.findAllProductsByStatusAndHot();
        JSONObject json = getJson("获取热门商品成功", true);
        json.put(DATA, hotProducts);
        return json;
    }
    @GetMapping("/hot/limit")
    public JSONObject findAllHotCommodityLimit(@RequestParam int limit){
        List<Product> hotProducts = productService.findProductsByStatusAndHotWithLimit(limit);
        System.out.println(hotProducts);
        JSONObject json = getJson("获取热门商品成功", true);
        json.put(DATA, hotProducts);
        return json;
    }
}
