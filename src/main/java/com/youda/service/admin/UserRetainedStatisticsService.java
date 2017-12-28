package com.youda.service.admin;

import com.youda.request.admin.UserStatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:41
 * @Version 1.0.0
 * @Instructions 定义
 */
public interface UserRetainedStatisticsService {

    /*定义统计自定义日期用户留存的功能*/
    ResponseEntity customDateRetainedStatistics(UserStatisticsRequest userStatisticsRequest);

    /*定义统计所有的用户留存的功能*/
    ResponseEntity allPlatformUserRetainedStatistics(UserStatisticsRequest userStatisticsRequest);
}
