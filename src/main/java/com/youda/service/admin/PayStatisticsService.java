package com.youda.service.admin;

import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:44
 * @Version 1.0.0
 * @Instructions 定义付费统计的接口定义
 */

public interface PayStatisticsService {

    /*定义今天支付率的统计*/
    ResponseEntity todayPayRateStatistics();

    /*定义昨天支付率的统计*/
    ResponseEntity yestodayPayRateStatistics();

    /*定义一周支付率的统计*/
    ResponseEntity aWeekPayRateStatistics();

    /*定义一月支付率的统计*/
    ResponseEntity aMonthPayRateStatistics();

    /*定义任意日期支付率的统计*/
    ResponseEntity customPayRateStatistics(String beginTime,String endTime);

    /*定义今天arpu的统计*/
    ResponseEntity todayArpuStatistics();

    /*定义昨天arpu的统计*/
    ResponseEntity yestodayArpuStatistics();

    /*定义一周的arpu的统计*/
    ResponseEntity aWeekArpuStatistics();

    /*定义一月的arpu统计*/
    ResponseEntity aMonthArpuStatistics();

    /*定义任意日期的arpu统计*/
    ResponseEntity customArpuStatistics(String beginTime,String endTime);

    /*定义今天arppu的统计*/
    ResponseEntity todayArppuStatistics();

    /*定义昨天的arppu统计*/
    ResponseEntity yestodayArppuStatistics();

    /*定义一周的arppu统计*/
    ResponseEntity aWeekArppuStatistics();

    /*定义一月的arppu统计*/
    ResponseEntity aMonthArppuStatistics();

    /*定义任意日期的arppu统计*/
    ResponseEntity customArppuStatistics(String beginTime,String endTime);

    /*定义今天支付玩家的统计*/
    ResponseEntity todayPayingPlayersStatistics();

    /*定义昨天支付玩家的统计*/
    ResponseEntity yestodayPayingPlayersStatistics();

    /*定义一周的玩家的统计*/
    ResponseEntity aWeekPayingPlayersStatistics();

    /*定义一月的玩家支付统计*/
    ResponseEntity aMonthPayingPlayersStatistics();

    /*定义任意日期玩家支付统计*/
    ResponseEntity customPayingPlayersStatistics(String beginTime,String endTime);
}


