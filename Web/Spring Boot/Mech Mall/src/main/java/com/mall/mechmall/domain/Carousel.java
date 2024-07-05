package com.mall.mechmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("carousel")
public class Carousel {

    @TableId(value = "id")
    private Long id; // 轮播图 ID

    @TableField(value = "img_url")
    private String imgUrl; // 图片 URL

    @TableField(value = "description")
    private String description; // 图片描述
}
