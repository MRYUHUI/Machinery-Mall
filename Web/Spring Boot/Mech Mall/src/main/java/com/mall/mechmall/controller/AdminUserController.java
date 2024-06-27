package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.mall.mechmall.utils.Consts.*;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuhui
 * @Date: 2024-06-26-13:54
 * @Description: 管理员的用户管理模块
 */

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) {
        boolean updated = userService.updateUserInfo(user);
        if (updated) {
            return "用户信息更新成功";
        } else {
            return "用户信息更新失败";
        }
    }

    /**
     * 获取所有用户信息
     * @return Object
     */
    @GetMapping("/allUsers")
    public Object findAllUsers() {
        List<Map<String, Object>> allUsers = userService.findAllUsers();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allUsers);
        return json;
    }
}
