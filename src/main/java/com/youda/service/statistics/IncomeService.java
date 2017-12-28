package com.youda.service.statistics;

import com.youda.request.statistics.StatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/20 14:52
 * @Version 1.0.0
 * @Instructions 定义收入统计接口
 */

public interface IncomeService {

    /*定义任意日期的收入统计*/
    ResponseEntity customTime(StatisticsRequest statisticsRequest);

    /*定义全部的收入统计*/
    ResponseEntity all(StatisticsRequest statisticsRequest);
}
