package com.mall.mechmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author:
 * @Date: 2024-06-25-21:48
 * @Description: 商品类型管理类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_category")
public class ProductCategory {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "parent_id")
    private Integer parentId;

    @TableField(value = "name")
    private String name;

    @TableField(value = "sort_order")
    private Integer sortOrder;

    @TableField(value = "status")
    private Boolean status;

    @TableField(value = "level")
    private Integer level;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "created")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updated")
    private Date updated;

}
