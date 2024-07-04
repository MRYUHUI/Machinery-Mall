package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mall.mechmall.utils.Consts.*;
/**
 * @Author:
 * @Date: 2024-07-02-20:46
 * @Description: 服务登录用户
 */
@RestController
@RequestMapping("/user/manage")
public class UserManageController {
    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public JSONObject updateUserById(@RequestBody User user) {
        if(userService.updateUserInfo(user)) {
            return getJson("编辑用户信息成功", true);
        }
        else{
            return getJson("编辑用户信息失败", false);
        }
    }
}
