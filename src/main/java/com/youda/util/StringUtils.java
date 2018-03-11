package com.youda.util;

/**
 * @CreateTime:2018/3/11 12:59
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 非空判断
 */
public class StringUtils {

    public static boolean isNotEmpty(String str) {
        if (str != null && str.trim().length()!=0) {
            return true;
        }
        return false;
    }
}
