package com.youda.service.admin;

import org.springframework.http.ResponseEntity;

public interface UserStatisticsService {

    /*定义新增用户统计*/
    ResponseEntity newUserStatistics();

    /*定义新增设备统计*/
    ResponseEntity newEquipmentStatistics();

    /*定义每天新增用户统计*/
    ResponseEntity everyDayNewUserStatistics();

    /*定义每周新增用户统计*/
    ResponseEntity everyWeekNewUserStatistics();

    /*定义每月新增用户统计*/
    ResponseEntity everyMonthNewUserStatistics();

    /*定义当日活跃用户统计*/
    ResponseEntity dengesActiveStatistics();

    /*定义当日付费用户统计*/
    ResponseEntity dengesPaymentStatistics();

    /*定义当日收入统计*/
    ResponseEntity dengesIncomeStatistics();

    /*定义用户留存统计*/
    ResponseEntity newUsersRetained();

}
