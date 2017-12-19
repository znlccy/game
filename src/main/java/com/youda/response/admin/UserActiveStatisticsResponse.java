package com.youda.response.admin;
/**
 * @Author Chencongye
 * @Date 2017/12/19 16:34
 * @Version 1.0.0
 * @Instructions 定义返回给客户端用户活跃数统计
 */

public class UserActiveStatisticsResponse {

    /*设置返回活跃用户数*/
    private String userActiveCount;

    /*声明以时间来划分*/
    private String ddate;

    /*实现活跃用户数的get方法*/
    public String getUserActiveCount() {
        return userActiveCount;
    }

    /*实现活跃用户数的set方法*/
    public void setUserActiveCount(String userActiveCount) {
        this.userActiveCount = userActiveCount;
    }

    /*实现返回日期的get方法*/
    public String getDdate() {
        return ddate;
    }

    /*实现返回日期的set方法*/
    public void setDdate(String ddate) {
        this.ddate = ddate;
    }
}
