package com.youda.service.admin;

import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:38
 * @Version 1.0.0
 * @Instructions 定义用户活跃数统计的接口
 */

public interface UserActiveStatisticsService {

    /*定义今天活跃用户统计*/
    ResponseEntity todayActiveUserStatistics();

    /*定义昨天活跃用户统计*/
    ResponseEntity yestodayActiveUserStatistics();

    /*定义每周活跃用户统计*/
    ResponseEntity aWeekActiveUserStatistics();

    /*定义一个月活跃用户统计*/
    ResponseEntity aMonthActiveUserStatistics();

    /*定义自选日期活跃用户统计*/
    ResponseEntity customDateActiveUserStatistics(String beginTime, String endTime);

    /*定义所有活跃用户统计*/
    ResponseEntity allActiveUserStatistics();
    
}
