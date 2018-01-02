package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:34
 * @Version 1.0.0
 * @Instructions 定义返回给客户端用户活跃数统计
 */

public class UserActiveResponse implements BaseResponse {

    /*设置返回活跃用户数*/
    private String userActiveCount;

    /*声明以时间来划分*/
    private String StatisticsDate;

    /*实现活跃用户数的get方法*/
    public String getUserActiveCount() {
        return userActiveCount;
    }

    /*实现活跃用户数的set方法*/
    public void setUserActiveCount(String userActiveCount) {
        this.userActiveCount = userActiveCount;
    }

    public String getStatisticsDate() {
        return StatisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
