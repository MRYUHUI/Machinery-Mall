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

    /**
     * 管理员跟新用户信息
     * @param user
     * @return Object
     */
    @PostMapping("/updateUserInfo")
    public Object updateUser(@RequestBody User user) {
        boolean updated = userService.updateUserInfo(user);
        if (updated) {
            // 获取更新后的用户信息
            User newUser = userService.findUserById(user.getId());
            JSONObject json = getJson("信息修改成功", true);
            json.put(DATA, newUser);
            return json;
        } else {
            return getJson("信息修改失败", false);
        }
    }
    @GetMapping("/deleteUser")
    public Object deleteUser(@RequestParam int id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return getJson("用户删除成功", true);
        } else {
            return getJson("用户删除失败", false);
        }
    }
    /**
     * 获取所有用户信息
     * @return Object
     */
    @GetMapping("/allUsers")
    public Object findAllUsers(@RequestParam int page, @RequestParam int size) {
        List<User> allUsers = userService.findAllUsers(page, size);
        int total = userService.countUsers();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allUsers);
        json.put("total", total);
        return json;
    }
}
