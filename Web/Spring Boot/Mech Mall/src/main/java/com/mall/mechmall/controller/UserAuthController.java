package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.User;
import com.mall.mechmall.security.PasswordEncoder;
import com.mall.mechmall.service.UserService;
import com.mall.mechmall.utils.Consts;
import com.mall.mechmall.utils.JwtUtils;
import com.mall.mechmall.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mall.mechmall.utils.Consts.*;

@RestController
@RequestMapping("/user/auth")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 用户注册
    @PostMapping("/do_register.do")
    public JSONObject register(@RequestBody User user) {
        JSONObject responseJson = new JSONObject();
        try {
            // 检查用户名是否存在
            if (userService.checkUserByAccount(user.getAccount())) {
                return Consts.getJson("用户名已存在", false);
            }
            // 检查邮箱是否存在
            if (userService.checkUserByEmail(user.getEmail())) {
                return Consts.getJson("邮箱已存在", false);
            }

            // 使用密码编码器加密密码
//            String encodedPassword = MD5Utils.MD5Encode(user.getPassword());
//            user.setPassword(encodedPassword);

            // 注册用户
            user.setRole(1); // 设置为普通用户角色
            user.setDel(0); // 设置未删除状态

            if (userService.insertUser(user)) {
                return Consts.getJson("注册成功", true);
            } else {
                return Consts.getJson("注册失败", false);
            }
        } catch (Exception e) {
            return Consts.getJson("注册失败：" + e.getMessage(), false);
        }
    }


    // 用户登录
    @PostMapping("/do_login.do")
    public JSONObject login(@RequestBody User user) {
        try {
            // 根据用户名查找用户
            User targetUser = userService.findUserByAccount(user.getAccount());

            if (targetUser == null) {
                throw new UsernameNotFoundException("用户不存在");
            }

            // 使用密码编码器验证密码
            if (!passwordEncoder.matches(user.getPassword(), targetUser.getPassword())) {
                return Consts.getJson("用户名或密码错误", false);
            }

            // 生成JWT Token
            String token = jwtUtils.generateToken(targetUser.getId(), targetUser.getAccount(), targetUser.getRole());
            JSONObject json = getJson("登录成功", true);
            json.put(DATA, targetUser);
            json.put(TOKEN, token);
            return json;

        } catch (Exception e) {
            return Consts.getJson("登录失败：" + e.getMessage(), false);
        }
    }
}
