package com.mall.mechmall.security;

import com.mall.mechmall.utils.MD5Utils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author:
 * @Date: 2024-06-29-13:12
 * @Description:
 */

/**
 * 自定义密码加密器，使用 MD5 加密密码比对
 */
@NoArgsConstructor
@Component
public class PasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }

        // 对接收到的原始密码进行 MD5 加密
//        String md5Password = MD5Utils.MD5Encode(rawPassword.toString());
//        if (md5Password == null)
//            return false;
//

        // 比对加密后的密码
        return rawPassword.equals(encodedPassword);
    }
}