package com.youda.service.admin;

import com.youda.request.admin.UserStatisticsRequest;
import org.springframework.http.ResponseEntity;

public interface UserNewStatisticsService {

    /*定义所有平台新增用户统计*/
    ResponseEntity allPlatformNewUserStatistics(UserStatisticsRequest userStatisticsRequest);

    /*定义自选日期新增用户统计*/
    ResponseEntity customDateNewUserStatistics(UserStatisticsRequest userStatisticsRequest);
}
