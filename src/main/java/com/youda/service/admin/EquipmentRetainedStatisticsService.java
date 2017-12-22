package com.youda.service.admin;

import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/21 14:12
 * @Version 1.0.0
 * @Instructions 实现新增设备统计服务接口
 */

public interface EquipmentRetainedStatisticsService {

    /*实现今天新增设备的统计*/
    ResponseEntity todayEquipmentStatistics();

    /*实现昨天新增设备的统计*/
    ResponseEntity yestodayEquipmentStatistics();

    /*实现一周的新增设备的统计*/
    ResponseEntity aWeekEquipmentStatistics();

    /*实现一个月新增设备的统计*/
    ResponseEntity aMonthEquipmentStatistics();

    /*实现自定义日期的新增设备的统计*/
    ResponseEntity customEquipmentStatistics(String beginTime,String endTime);

    /*实现全部的新增设备的统计*/
    ResponseEntity allEquipmentStatistics();

}

