package com.mall.mechmall.controller;

import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yuhui
 * @Date: 2024-06-26-13:54
 * @Description: 管理员的用户管理模块
 */

@RestController
@RequestMapping("/mgr/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update.do")
    public String updateUser(@RequestBody User user) {
        boolean updated = userService.updateUserInfo(user);
        if (updated) {
            return "用户信息更新成功";
        } else {
            return "用户信息更新失败";
        }
    }

    // 其他用户管理功能方法
}
