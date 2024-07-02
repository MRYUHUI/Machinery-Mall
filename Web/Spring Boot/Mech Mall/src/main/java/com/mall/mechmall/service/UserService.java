package com.mall.mechmall.service;

import com.mall.mechmall.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date: 2024-06-25-21:58
 * @Description:
 */
public interface UserService {
    User findUserByAccountAndPwd(String account, String password);
    Boolean checkUserByAccount(String account);
    Boolean checkUserByEmail(String email);
    Boolean checkUserByPhone(String phone);
    Boolean insertUser(User user);
    String findUserQuestion(String account);
    Boolean checkUserAnswer(String account, String asw);
    Boolean updatePasswordByAccount(String account, String password);
    String checkPassword(String account);
    Boolean updateUserInfo(User user);
    List<User> findAllUsers(int page, int size);
    int countUsers();
    Boolean deleteUser(Integer id);
    User findUserById(Integer id);
    User findUserByAccount(String account);
    List<User> searchUsers(String keyword, int page, int size);
    int countUsersByKeyword(String keyword);
}
