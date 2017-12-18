package com.youda.response.admin;

public class UserRetainedStatisticsResponse {

    /*定义用户留存统计的变量*/
    private String userRetainedCount;

    /*定义用户留存统计的日期*/
    private String ddate;

    /*实现用户留存统计的get方法*/
    public String getUserRetainedCount() {
        return userRetainedCount;
    }

    /*实现用户留存统计的set方法*/
    public void setUserRetainedCount(String userRetainedCount) {
        this.userRetainedCount = userRetainedCount;
    }

    /*实现用户留存的date的get方法*/
    public String getDdate() {
        return ddate;
    }

    /*实现用户留存的date的set方法*/
    public void setDdate(String ddate) {
        this.ddate = ddate;
    }
}
