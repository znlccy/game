package com.youda.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @CreateTime:2018/3/11 14:15
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 安全工具
 */

public class SecurityUtil {
    
    /**
     * @comment: md5 md5加密
     * @param: [password]
     * @return: java.lang.String
     */
    public static String md5(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(userPassword.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    /**
     * @comment: md5 md5加密
     * @param: []
     * @return: java.lang.String
     */
    public static String md5(String userName, String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(userName.getBytes());
        md.update(userPassword.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }
}
