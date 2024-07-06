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
 * @Author: thh
 * @Date: 2024/7/3
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shipping_address")
public class ShippingAddress {
    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("mobile")
    private String mobile;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("district")
    private String district;

    @TableField("addr")
    private String addr;

    @TableField("zip")
    private String zip;

    @TableField("dfault")
    private Integer dfault;

    @TableField("isDel")
    private Integer isDel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
}
