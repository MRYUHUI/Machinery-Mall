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
 * @Author: yuhui
 * @Date: 2024-06-25-21:48
 * @Description: 用户类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class User {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "account")
    private String account;

    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "question")
    private String question;

    @TableField(value = "asw")
    private String asw;

    @TableField(value = "role")
    private Integer role;

    @TableField(value = "age")
    private Integer age;

    @TableField(value = "sex")
    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "del")
    private Integer del;

    @TableField(value = "name")
    private String name;
}
