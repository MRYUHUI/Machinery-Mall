package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.Carousel;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.mapper.CarouselMapper;
import com.mall.mechmall.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:
 * @Date: 2024-07-04-22:14
 * @Description:
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<Carousel> findAllCarousels() {
        return carouselMapper.findAllCarousels();
    }
}
