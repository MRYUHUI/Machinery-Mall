package com.mall.mechmall.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-11:18
 * @Description: 时间格式化工具类
 */
public class DateUtils {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将 LocalDateTime 格式化为字符串，默认格式为 "yyyy-MM-dd HH:mm:ss"
     * @param dateTime LocalDateTime 对象
     * @return 格式化后的字符串
     */
    public static String date2Str(LocalDateTime dateTime) {
        return date2Str(dateTime, DEFAULT_PATTERN);
    }

    /**
     * 将 LocalDateTime 格式化为字符串
     * @param dateTime LocalDateTime 对象
     * @param pattern 格式化模式
     * @return 格式化后的字符串
     */
    public static String date2Str(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 将字符串解析为 LocalDateTime 对象，默认格式为 "yyyy-MM-dd HH:mm:ss"
     * @param str 字符串
     * @return LocalDateTime 对象
     */
    public static LocalDateTime str2Date(String str) {
        return str2Date(str, DEFAULT_PATTERN);
    }

    /**
     * 将字符串解析为 LocalDateTime 对象
     * @param str 字符串
     * @param pattern 解析模式
     * @return LocalDateTime 对象
     */
    public static LocalDateTime str2Date(String str, String pattern) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
