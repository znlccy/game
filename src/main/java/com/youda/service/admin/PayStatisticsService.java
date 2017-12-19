package com.youda.service.admin;

import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:44
 * @Version 1.0.0
 * @Instructions 定义付费统计的接口定义
 */

public interface PayStatisticsService {

    /*定以日付费率的统计*/
    ResponseEntity dayPayRateStatistics();

    /*定义日ARPU统计*/
    ResponseEntity dayArpuStatistics();

    /*定义日ARPPU统计*/
    ResponseEntity dayArppuStatistics();
}


