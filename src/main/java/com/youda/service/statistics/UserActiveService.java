package com.youda.service.statistics;

import com.youda.request.statistics.PeriodRequest;
import com.youda.request.statistics.StatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:38
 * @Version 1.0.0
 * @Instructions 定义用户活跃数统计的接口
 */

public interface UserActiveService {

    /*定义自选日期活跃用户统计*/
    ResponseEntity customTime(StatisticsRequest statisticsRequest);

    /*定义所有活跃用户统计*/
    ResponseEntity all(StatisticsRequest statisticsRequest);

    /*定义一天24小时时间段统计*/
    ResponseEntity period(PeriodRequest periodRequest);
}
