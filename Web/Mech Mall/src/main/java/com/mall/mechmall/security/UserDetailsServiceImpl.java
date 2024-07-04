package com.mall.mechmall.security;

import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:
 * @Date: 2024-06-29-14:20
 * @Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名加载用户的详细信息
     *
     * @param username 用户名
     * @return UserDetails 用户详情对象
     * @throws UsernameNotFoundException 如果用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername:-----------------");
        // 调用UserService的findUserByAccount方法查找用户
        User user = userService.findUserByAccount(username);
        System.out.println(user);
        // 如果用户不存在，抛出UsernameNotFoundException异常
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 根据用户角色设置相应的权限
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        if (user.getRole() == 2) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        // 创建并返回一个实现了UserDetails接口的对象
        return new org.springframework.security.core.userdetails.User(
                user.getAccount(), // 用户名
                user.getPassword(), // 密码
                authorities // 角色列表
        );
    }
}
