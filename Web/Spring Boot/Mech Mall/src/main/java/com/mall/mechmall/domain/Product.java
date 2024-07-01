package com.mall.mechmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("products")
public class Product {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "product_id")
    private Integer productId;

    @TableField(value = "parts_id")
    private Integer partsId;

    @TableField(value = "icon_url" )
    private String iconUrl;

    @TableField(value = "sub_images")
    private String subImages;

    @TableField(value = "detail")
    private String detail;

    @TableField(value = "spec_param")
    private String specParam;

    @TableField(value = "price")
    private float price;

    @TableField(value = "stock")
    private Integer stock;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "is_hot")
    private Integer isHot;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updated")
    private Date updated;


}
