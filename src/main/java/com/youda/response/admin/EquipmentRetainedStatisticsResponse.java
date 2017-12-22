package com.youda.response.admin;

/**
 * @Author Chencongye
 * @Date 2017/12/15 17:11
 * @Version 1.0.0
 * @Instructions 定义返回给客户端
 */

public class EquipmentRetainedStatisticsResponse {

    /*新增设备统计*/
    private String newEquipmentCount;

    /*新增设备对应的日期*/
    private String ddate;

    /*实现新增设备的get方法*/
    public String getNewEquipmentCount() {
        return newEquipmentCount;
    }

    /*实现新增设备的set方法*/
    public void setNewEquipmentCount(String newEquipmentCount) {
        this.newEquipmentCount = newEquipmentCount;
    }

    /*实现新增设备对应日期的get方法*/
    public String getDdate() {
        return ddate;
    }

    /*实现新增设备的对应日期的set方法*/
    public void setDdate(String ddate) {
        this.ddate = ddate;
    }
}
