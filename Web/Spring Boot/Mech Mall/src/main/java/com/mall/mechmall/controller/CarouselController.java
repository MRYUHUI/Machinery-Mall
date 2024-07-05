package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Carousel;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;
import java.util.List;

/**
 * @Author:
 * @Date: 2024-07-04-22:15
 * @Description:
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @GetMapping("/all")
    public JSONObject findAllCarousel(){
        List<Carousel> allCarousels = carouselService.findAllCarousels();
        JSONObject json = getJson("获取轮播图成功", true);
        json.put(DATA, allCarousels);
        return json;
    }
}
