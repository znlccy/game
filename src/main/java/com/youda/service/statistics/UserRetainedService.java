package com.youda.service.statistics;

import com.youda.request.statistics.StatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:41
 * @Version 1.0.0
 * @Instructions 定义
 */
public interface UserRetainedService {

    /*定义统计自定义日期用户留存的功能*/
    ResponseEntity customTime(StatisticsRequest statisticsRequest);

    /*定义统计所有的用户留存的功能*/
    ResponseEntity all(StatisticsRequest statisticsRequest);
}
