package com.youda.response.statistics;

import com.youda.response.BaseResponse;

public class UserRetainedResponse implements BaseResponse  {

    /*定义用户留存统计的变量*/
    private String userRetainedCount;

    /*定义用户留存统计的日期*/
    private String StatisticsDate;

    /*实现用户留存统计的get方法*/
    public String getUserRetainedCount() {
        return userRetainedCount;
    }

    /*实现用户留存统计的set方法*/
    public void setUserRetainedCount(String userRetainedCount) {
        this.userRetainedCount = userRetainedCount;
    }

    public String getStatisticsDate() {
        return StatisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
