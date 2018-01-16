package com.youda.response.statistics;

/**
 * @Author Chencongye
 * @Date 2018/1/17 0:10
 * @Version 1.0.0
 * @Instructions 机型返回数据
 */

public class ModelsResponse {

    /*定义机型返回给客户端数量*/
    private String modelsCount;

    /*定义机型返回给客户端日期*/
    private String StatisticsDate;

    /*实现获取机型数量的get方法*/
    public String getModelsCount() {
        return modelsCount;
    }

    /*实现获取机型数量的set方法*/
    public void setModelsCount(String modelsCount) {
        this.modelsCount = modelsCount;
    }

    /*实现统计机型的日期的get方法*/
    public String getStatisticsDate() {
        return StatisticsDate;
    }

    /*实现统计机型的日期的set方法*/
    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
