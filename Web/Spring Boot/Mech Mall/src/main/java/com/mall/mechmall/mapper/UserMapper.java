package com.mall.mechmall.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mall.mechmall.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-21:52
 * @Description: 用户持久层
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password}")
    public User fndUserByAccountAndPwd(@Param("account") String account, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM users WHERE account = #{account}")
    public int checkUserByAccount(@Param("account") String account);

    @Select("SELECT COUNT(*) FROM users WHERE email = #{email}")
    public int checkUserByEmail(@Param("email") String email);

    @Select("SELECT COUNT(*) FROM users WHERE phone = #{phone}")
    public int checkUserByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO users (account, password, email, phone, question, asw, role, age, sex, create_time, update_time, del, name) " +
            "VALUES (#{account}, #{password}, #{email}, #{phone}, #{question}, #{asw}, #{role}, #{age}, #{sex}, NOW(), Now(), #{del}, #{name})")
    public int insertUser(User user);

    @Select("SELECT question FROM users WHERE account = #{account}")
    public String fndUserQuestion(@Param("account") String account);

    @Select("SELECT COUNT(*) FROM users WHERE account = #{account} AND asw = #{asw}")
    public int checkUserAnswer(@Param("account") String account, @Param("asw") String asw);

    @Update("UPDATE users SET password = #{password} WHERE account = #{account}")
    public int updatePasswordByAccount(@Param("account") String account, @Param("password") String password);

    @Select("SELECT password FROM users WHERE account = #{account}")
    public String checkPassword(@Param("account") String account);

    @Update({
            "<script>",
            "UPDATE users",
            "<set>",
            "<if test='account != null'>account = #{account},</if>",
            "<if test='password != null'>password = #{password},</if>",
            "<if test='email != null'>email = #{email},</if>",
            "<if test='phone != null'>phone = #{phone},</if>",
            "<if test='question != null'>question = #{question},</if>",
            "<if test='asw != null'>asw = #{asw},</if>",
            "<if test='role != null'>role = #{role},</if>",
            "<if test='age != null'>age = #{age},</if>",
            "<if test='sex != null'>sex = #{sex},</if>",
            "<if test='name != null'>name = #{name},</if>",
            "update_time = NOW(),",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    public int updateUserInfo(User user);


    @Select("SELECT id, account, email, phone, age, sex, name FROM users WHERE role = 1 LIMIT #{offset}, #{size}")
    public List<User> findAllUsers(@Param("offset") int offset, @Param("size") int size);


    @Select("SELECT COUNT(*) FROM users WHERE role = 1")
    public int countUsers();

    @Delete("DELETE FROM users WHERE id = #{id}")
    public int deleteUser(@Param("id") Integer id);

    @Select("SELECT id, account, email, phone, age, sex, name FROM users WHERE id = #{id}")
    public User fndUserById(@Param("id") Integer id);

    @Select("SELECT * FROM users WHERE account = #{account}")
    public User fndUserByAccount(@Param("account") String account);

    @Select("SELECT * FROM users WHERE account LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%') LIMIT #{offset}, #{size}")
    List<User> searchUsers(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM users WHERE account LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%')")
    int countUsersByKeyword(String keyword);

}
