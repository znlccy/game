package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * @Author Chencongye
 * @Date 2017/12/15 17:11
 * @Version 1.0.0
 * @Instructions 定义返回给客户端
 */

public class EquipmentRetainedResponse implements BaseResponse{

    /*新增设备统计*/
    private String equipmentRate;

    /*新增设备对应的日期*/
    private String StatisticsDate;

    /*实现设备留存率的get方法*/
    public String getEquipmentRate() {
        return equipmentRate;
    }

    /*实现设备留存率的set方法*/
    public void setEquipmentRate(String equipmentRate) {
        this.equipmentRate = equipmentRate;
    }

    public String getStatisticsDate() {
        return StatisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
