package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.Carousel;
import com.mall.mechmall.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:
 * @Date: 2024-07-04-22:13
 * @Description:
 */
@Mapper
public interface CarouselMapper {
    @Select("SELECT * FROM carousel")
    public List<Carousel> findAllCarousels();
}
