package com.mall.mechmall.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-10:43
 * @Description: 编辑运算工具类
 */
public class BigDecimalUtil {
    // 加法
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    // 减法
    public static BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }

    // 乘法
    public static BigDecimal multiply(BigDecimal num1, BigDecimal num2) {
        return num1.multiply(num2);
    }

    // 除法，默认保留2位小数
    public static BigDecimal divide(BigDecimal num1, BigDecimal num2) {
        return divide(num1, num2, 2);
    }

    // 除法，指定保留小数位数
    public static BigDecimal divide(BigDecimal num1, BigDecimal num2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Scale must be a non-negative integer");
        }
        return num1.divide(num2, scale, RoundingMode.HALF_UP);
    }

}
