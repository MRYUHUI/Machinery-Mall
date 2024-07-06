package com.mall.mechmall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-11:18
 * @Description: 时间格式化工具类
 */
public class DateUtils {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间的字符串，格式为 "yyyy-MM-dd HH:mm:ss"
     *
     * @return 当前时间的字符串
     */
    public static String getCurrentTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间的 Date 对象
     *
     * @return 当前时间的 Date 对象
     */
    public static Date getCurrentDate() {
        return new Date();
    }
}
