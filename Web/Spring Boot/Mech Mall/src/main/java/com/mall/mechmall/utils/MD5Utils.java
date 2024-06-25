package com.mall.mechmall.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-10:57
 * @Description: MD5 加密工具类(大写)
 */
public class MD5Utils {

    /**
     * 对字符串进行 MD5 加密
     * @param str 要加密的字符串
     * @return 加密后的 MD5 字符串
     */
    public static String MD5Encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();

            StringBuilder buf = new StringBuilder();
            for (byte b : byteDigest) {
                int i = b & 0xff;
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i).toUpperCase());
            }
            // 返回 32 位的加密结果
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // 或者抛出自定义异常，根据实际需求处理
        }
    }
}