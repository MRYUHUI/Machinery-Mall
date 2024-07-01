package com.mall.mechmall.controller;
import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.UserService;
import static com.mall.mechmall.utils.Consts.*;

import com.mall.mechmall.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @Author:
 * @Date: 2024-06-25-22:03
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 用户注册
    @PostMapping("/do_register.do")
    public Object register(@RequestBody User user) {
        // 检查用户名是否存在
        if (userService.checkUserByAccount(user.getAccount())) {
            return getJson("用户名已存在", false);
        }
        // 检查邮箱是否存在
        if (userService.checkUserByEmail(user.getEmail())) {
            return getJson("邮箱已存在", false);
        }
        user.setPassword(MD5Utils.MD5Encode(user.getPassword()));
        // 注册用户
        // 注册着位普通用户
        user.setRole(1);
        user.setDel(0);
        if (userService.insertUser(user)) {
            return getJson("注册成功", true);
        } else {
            return getJson("注册失败", false);
        }
    }

    // 用户登录
    @PostMapping("/do_login.do")
    public Object login(@RequestBody User user) {
//        System.out.println(user);
        Boolean isUserExist = userService.checkUserByAccount(user.getAccount());
        if(!isUserExist){
            return getJson("用户不存在！", false);
        }
        user.setPassword(MD5Utils.MD5Encode(user.getPassword()));
        User targetUser = userService.findUserByAccountAndPwd(user.getAccount(), user.getPassword());
        if (targetUser != null) {
            JSONObject json = getJson("登录成功", true);
            json.put(DATA, targetUser);
            return json;
        } else {
            return getJson("用户名或密码错误！", false);
        }
    }
}
