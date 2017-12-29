package com.youda.service.statistics;

import com.youda.request.statistics.StatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:44
 * @Version 1.0.0
 * @Instructions 定义付费统计的接口定义
 */

public interface PayService {


    /*定义任意日期支付率的统计*/
    ResponseEntity customPayRateTime(StatisticsRequest statisticsRequest);

    /*定义全部支付率的统计*/
    ResponseEntity allPayRate(StatisticsRequest statisticsRequest);


    /*定义任意日期的arpu统计*/
    ResponseEntity customArpuTime(StatisticsRequest statisticsRequest);

    /*定义全部arpu统计*/
    ResponseEntity allArpu(StatisticsRequest statisticsRequest);


    /*定义任意日期的arppu统计*/
    ResponseEntity customArppuTime(StatisticsRequest statisticsRequest);

    /*定义全部的arpu统计*/
    ResponseEntity allArppu(StatisticsRequest statisticsRequest);


    /*定义任意日期玩家支付统计*/
    ResponseEntity customPlayersTime(StatisticsRequest statisticsRequest);

    /*定义全部的付费玩家统计*/
    ResponseEntity allPlayers(StatisticsRequest statisticsRequest);
}


