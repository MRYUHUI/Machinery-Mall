package com.mall.mechmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_items")
public class OrderItem {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "order_id")
    private Integer orderId;

    @TableField(value = "goods_id")
    private Integer goodsId;

    @TableField(value = "goods_name")
    private String goodsName;

    @TableField(value = "icon_url")
    private String iconUrl;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "quantity")
    private Integer quantity;

    @TableField(value = "total_price")
    private double totalPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updated")
    private Date updated;
}
