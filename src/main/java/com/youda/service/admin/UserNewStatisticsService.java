package com.youda.service.admin;

import com.youda.request.admin.NewUserStatisticsRequest;
import org.springframework.http.ResponseEntity;

public interface UserNewStatisticsService {

    /*定义所有新增用户统计*/
    ResponseEntity allNewUserStatistics();

    /*定义自选日期新增用户统计*/
    ResponseEntity customDateNewUserStatistics(NewUserStatisticsRequest newUserStatisticsRequest);
}
