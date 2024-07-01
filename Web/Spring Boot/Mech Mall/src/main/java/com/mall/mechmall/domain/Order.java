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
import com.baomidou.mybatisplus.annotation.TableField;
        import com.baomidou.mybatisplus.annotation.TableId;
        import com.baomidou.mybatisplus.annotation.TableName;
        import com.fasterxml.jackson.annotation.JsonFormat;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-21:48
 * @Description: 用户类Java实体类的定义，用于映射数据库中的订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {
//    private Long id;
//    private Integer order_no;
//    private Integer uid;
//    private Integer addrId;
//    private BigDecimal amount;
//    private Integer type;
//    private BigDecimal freight;
//    private Integer status;
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "order_no")
    private Integer orderNo; // bigint对应Long

    @TableField(value = "uid")
    private long uid; // int对应Integer

    @TableField(value = "addr_id")
    private double addrId; // int对应Integer

    @TableField(value = "amount")
    private double amount; // decimal对应BigDecimal

    @TableField(value = "type")
    private Integer type; // int对应Integer

    @TableField(value = "freight")
    public double freight; // int对应Integer

    @TableField(value = "status")
    private Integer status; // int对应Integer

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time")
    private Date updateTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "payment_time")
    private Date paymentTime; // datetime对应Date

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "delivery_time")
    private Date deliveryTime; // datetime对应Date

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "finish_time")
    private Date finishTime; // datetime对应Date

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "close_time")
    private Date closeTime; // datetime对应Date




        // 其他属性和方法



///////////

//    @TableField(value = "asw")
//    private String asw;
//
//    @TableField(value = "role")
//    private Integer role;
//
//    @TableField(value = "age")
//    private Integer age;
//
//    @TableField(value = "sex")
//    private Integer sex;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @TableField(value = "create_time")
//    private Date createTime;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @TableField(value = "update_time")
//    private Date updateTime;
//
//    @TableField(value = "del")
//    private Integer del;
//
//    @TableField(value = "name")
//    private String name;
}
