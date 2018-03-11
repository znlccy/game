package com.youda.util;

/**
 * @CreateTime:2018/3/11 14:11
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 随机数工具
 */

public class RandomTools {

    /**
     * @comment: randomCode 随机生成留个数字
     * @param: []
     * @return: java.lang.String
     */
    public static String randomCode() {
       Integer res = (int)(Math.random()*1000000);
       return res+"";
    }
}
