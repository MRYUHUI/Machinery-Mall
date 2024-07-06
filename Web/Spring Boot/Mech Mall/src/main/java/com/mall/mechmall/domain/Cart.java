package com.mall.mechmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cart")
public class Cart {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "product_id")
    private Integer productId;

    @TableField(value = "quantity")
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updated")
    private Date updated;
}
