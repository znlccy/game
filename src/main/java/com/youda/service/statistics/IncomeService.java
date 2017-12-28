package com.youda.service.statistics;

import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/20 14:52
 * @Version 1.0.0
 * @Instructions 定义收入统计接口
 */

public interface IncomeService {

    /*定义今天的收入统计*/
    ResponseEntity todayIncomeStatistics();

    /*定义昨天的收入统计*/
    ResponseEntity yestodayIncomeStatistics();

    /*定义一周的收入统计*/
    ResponseEntity aWeekIncomeStatistics();

    /*定义一个月的收入统计*/
    ResponseEntity aMonthIncomeStatistics();

    /*定义任意日期的收入统计*/
    ResponseEntity customTime(String beginTime,String endTime);

    /*定义全部的收入统计*/
    ResponseEntity all();
}
