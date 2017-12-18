package com.youda.service.admin;

import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:41
 * @Version 1.0.0
 * @Instructions 定义
 */
public interface UserRetainedStatisticsService {

    /*定义统计今天用户留存的功能*/
    ResponseEntity todayUserRetainedStatistics();

    /*定义统计昨天的用户留存的功能*/
    ResponseEntity yestodayUserRetainedStatistics();

    /*定义统计一周的用户留存的功能*/
    ResponseEntity aWeekUserRetainedStatistics();

    /*定义统计一月的用户留存的功能*/
    ResponseEntity aMonthUserRetainedStatistics();

    /*定义统计自定义日期用户留存的功能*/
    ResponseEntity customDateRetainedStatistics(String beginTime,String endTime);

    /*定义统计所有的用户留存的功能*/
    ResponseEntity allUserRetainedStatisticsStatistics();
}
