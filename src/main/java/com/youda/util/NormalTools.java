package com.youda.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @CreateTime:2018/3/11 13:58
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 普通工具
 */

public class NormalTools {

    public static String getFileType(String fileName) {

        if (fileName != null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }

    /**
     * @comment: isImageFile 判断文件是否为图片文件
     * @param: []
     * @return: java.lang.Boolean
     */
    public static Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg",".jpeg",".png",".gif",".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @comment: curDate 当前时间
     * @param: [pattern]
     * @return: java.lang.String
     */
    public static String curDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * @comment: curDate 生成当前时间
     * @param: []
     * @return: java.lang.String
     */
    public static String curDate() {
        return curDate("yyyy-MM-dd HH:mm:ss");
    }

}
