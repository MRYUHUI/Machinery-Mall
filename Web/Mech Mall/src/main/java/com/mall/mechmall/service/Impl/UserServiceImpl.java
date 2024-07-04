package com.mall.mechmall.service.Impl;

import com.mall.mechmall.service.UserService;


import com.mall.mechmall.domain.User;
import com.mall.mechmall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date: 2024-06-25-21:59
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByAccountAndPwd(String account, String password) {
        return userMapper.fndUserByAccountAndPwd(account, password);
    }

    @Override
    public Boolean checkUserByAccount(String account) {
        return userMapper.checkUserByAccount(account) > 0;
    }

    @Override
    public Boolean checkUserByEmail(String email) {
        return userMapper.checkUserByEmail(email) > 0;
    }

    @Override
    public Boolean checkUserByPhone(String phone) {
        return userMapper.checkUserByPhone(phone) > 0;
    }

    @Override
    public Boolean insertUser(User user) {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public String findUserQuestion(String account) {
        return userMapper.fndUserQuestion(account);
    }

    @Override
    public Boolean checkUserAnswer(String account, String asw) {
        return userMapper.checkUserAnswer(account, asw) > 0;
    }

    @Override
    public Boolean updatePasswordByAccount(String account, String password) {
        return userMapper.updatePasswordByAccount(account, password) > 0;
    }

    @Override
    public String checkPassword(String account) {
        return userMapper.checkPassword(account);
    }

    @Override
    public Boolean updateUserInfo(User user) {
        return userMapper.updateUserInfo(user) > 0;
    }

    /**
     * 获取所有普通用户
     * @param page
     * @param size
     * @return List<Map<String, Object>>
     */
    @Override
    public List<User> findAllUsers(int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.findAllUsers(offset, size);
    }
    /**
     * 获取普通用户的数量
     * @return int
     */
    @Override
    public int countUsers() {
        return userMapper.countUsers();
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id) > 0;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.fndUserById(id);
    }

    @Override
    public User findUserByAccount(String account) {
        return userMapper.fndUserByAccount(account);
    }

    @Override
    public List<User> searchUsers(String keyword, int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.searchUsers(keyword, offset, size);
    }



    @Override
    public int countUsersByKeyword(String keyword) {
        return userMapper.countUsersByKeyword(keyword);
    }

}
