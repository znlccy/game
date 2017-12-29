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
    private String ddate;

    /*实现设备留存率的get方法*/
    public String getEquipmentRate() {
        return equipmentRate;
    }

    /*实现设备留存率的set方法*/
    public void setEquipmentRate(String equipmentRate) {
        this.equipmentRate = equipmentRate;
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
