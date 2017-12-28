package com.youda.service.statistics;

import com.youda.request.statistics.UserStatisticsRequest;
import org.springframework.http.ResponseEntity;

public interface UserNewService {

    /*定义所有平台新增用户统计*/
    ResponseEntity all(UserStatisticsRequest userStatisticsRequest);

    /*定义自选日期新增用户统计*/
    ResponseEntity customTime(UserStatisticsRequest userStatisticsRequest);
}
