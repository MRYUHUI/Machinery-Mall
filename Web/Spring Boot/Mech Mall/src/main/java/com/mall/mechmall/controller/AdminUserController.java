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
    /**
     * 搜索用户信息
     * @param keyword 搜索关键词（用户名或姓名）
     * @param page 当前页码
     * @param size 每页大小
     * @return 符合搜索条件的用户信息和总数的 JSON 对象
     */
    @GetMapping("/searchUsers")
    public Object searchUsers(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        List<User> searchResult = userService.searchUsers(keyword, page, size);
        int total = userService.countUsersByKeyword(keyword);
        JSONObject json = getJson("搜索成功", true);
//        System.out.println("=========="+ searchResult);
        json.put(DATA, searchResult);
        json.put("total", total);
        return json;
    }

}
