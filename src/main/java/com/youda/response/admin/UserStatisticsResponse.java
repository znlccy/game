package com.youda.response.admin;

import com.youda.response.BaseResponse;

/**
 * @Author Chencongye
 * @Date 2017/12/14 13:30
 * @Version 1.0.0
 * @Instructions 设置用户统计响应返回的数据,用户数据报表
 */

public class UserStatisticsResponse implements BaseResponse {

    /*设置返回新增用户数*/
    private String newUserCount;

    /*声明以时间来划分*/
    private String time;

    /*实现新增用户数量的get方法*/
    public String getNewUserCount() {
        return newUserCount;
    }

    /*实现新增用户数量的set方法*/
    public void setNewUserCount(String newUserCount) {
        this.newUserCount = newUserCount;
    }

    /*实现新增用户对应时间的get方法*/
    public String getTime() {
        return time;
    }

    /*实现新增用户对应时间的set方法*/
    public void setTime(String time) {
        this.time = time;
    }
}
