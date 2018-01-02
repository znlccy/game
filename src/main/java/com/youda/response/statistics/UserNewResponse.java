package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * @Author Chencongye
 * @Date 2017/12/14 13:30
 * @Version 1.0.0
 * @Instructions 设置用户统计响应返回的数据,用户数据报表
 */

public class UserNewResponse implements BaseResponse {

    /*设置返回新增用户数*/
    private String userNewCount;

    /*声明以时间来划分*/
    private String StatisticsDate;

    /*实现新增用户的get方法*/
    public String getUserNewCount() {
        return userNewCount;
    }

    /*实现新增用户的set方法*/
    public void setUserNewCount(String userNewCount) {
        this.userNewCount = userNewCount;
    }

    public String getStatisticsDate() {
        return StatisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
