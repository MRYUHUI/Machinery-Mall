package com.mall.mechmall.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.FileSystemUtils;

import java.io.File;

public class Consts {
    //    返回碼
    public static final String CODE = "code";
    //    返回信息
    public static final String MSG = "message";

    public static final String SUCCESS = "success";

    public static final String ERROR = "error";

    public static final String WARNING = "warning";

    public static final String DATA = "data";

    public static final String STATUS = "status";

    public static final String TYPE = "type";

    public static final int CODE_404 = 404;

    public static final int CODE_200 = 200;

    public static final int CODE_500 = 500;

    public static final String TOKEN = "token";


    public static Boolean deleteFile(String path) {
        // 构建完整的文件路径，替换路径中的分隔符
        String filePath = System.getProperty("user.dir") + path;
        File file = new File(filePath);

        // 输出调试信息，确保路径正确
        System.out.println("Trying to delete file at path: " + filePath);

        // 检查文件是否存在
        if (!file.exists()) {
            System.out.println("File does not exist: " + filePath);
            return true; // 文件不存在，直接返回true
        }

        // 尝试删除文件或目录
        boolean result = FileSystemUtils.deleteRecursively(file);
        System.out.println("Deletion result: " + result);
        return result;
    }

    public static JSONObject getJson(String msg, boolean tag) {
        JSONObject jsonObject = new JSONObject();
        if (tag) {
            jsonObject.put(STATUS, 0);
            jsonObject.put(Consts.MSG, msg);
            jsonObject.put(Consts.SUCCESS, true);
            jsonObject.put(TYPE, SUCCESS);
        } else {
            jsonObject.put(STATUS, 1);
            jsonObject.put(Consts.MSG, msg);
            jsonObject.put(TYPE, ERROR);
            jsonObject.put(Consts.SUCCESS, false);
        }
        return jsonObject;
    }
}
