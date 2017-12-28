package com.youda.service.admin;

import com.youda.request.admin.UserStatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:38
 * @Version 1.0.0
 * @Instructions 定义用户活跃数统计的接口
 */

public interface UserActiveStatisticsService {

    /*定义自选日期活跃用户统计*/
    ResponseEntity customDateActiveUserStatistics(UserStatisticsRequest userStatisticsRequest);

    /*定义所有活跃用户统计*/
    ResponseEntity allPlatformActiveUserStatistics(UserStatisticsRequest userStatisticsRequest);
    
}
