package com.mall.mechmall.service;

import com.mall.mechmall.domain.Carousel;
import com.mall.mechmall.domain.Product;

import java.util.List;

/**
 * @Author:
 * @Date: 2024-07-04-22:14
 * @Description:
 */
public interface CarouselService {
    public List<Carousel> findAllCarousels();
}
