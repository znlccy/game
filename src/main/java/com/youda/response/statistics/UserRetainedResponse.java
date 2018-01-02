package com.youda.response.statistics;

import com.youda.response.BaseResponse;

public class UserRetainedResponse implements BaseResponse  {

    /*定义用户留存统计的变量*/
    private String userRate;

    /*定义用户留存统计的日期*/
    private String StatisticsDate;

    public String getUserRate() {
        return userRate;
    }

    public void setUserRate(String userRate) {
        this.userRate = userRate;
    }

    public String getStatisticsDate() {
        return StatisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
