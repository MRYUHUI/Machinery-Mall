package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;

@RestController
@RequestMapping("/user/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /*查询所有分类*/
    @GetMapping("/getProductCategories")
    public Object getProductCategories(@RequestParam int parentId){
        List<ProductCategory> productCategories = productCategoryService.getProductCategories(parentId);
        int total = productCategoryService.countProductCategorys();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, productCategories);
        json.put("total", total);
        return json;
    }
}
