package com.youda.service.admin;

import com.youda.request.admin.NewUserStatisticsRequest;
import org.springframework.http.ResponseEntity;

public interface UserNewStatisticsService {

    /*定义新增用户统计*/
    ResponseEntity nearlyAMonthNewUserStatistics();

    /*定义今天新增用户统计*/
    ResponseEntity todayNewUserStatistics();

    /*定义昨天新增用户统计*/
    ResponseEntity yestodayNewUserStatistics();

    /*定义每周新增用户统计*/
    ResponseEntity everyWeekNewUserStatistics();

    /*定义所有新增用户统计*/
    ResponseEntity allNewUserStatistics();

    /*定义自选日期新增用户统计*/
    ResponseEntity customDateNewUserStatistics(NewUserStatisticsRequest newUserStatisticsRequest);
}
