package com.youda.controller.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.service.statistics.PayService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Chencongye
 * @Date 2017/12/13 16:10
 * @Version 1.0.0
 * @Instructions 实现付费和收益统计
 */

@RestController
@RequestMapping(value = "/statistics/pay")
public class PayController {

    /*实现支付统计的自动依赖注入*/
    @Autowired
    PayService payService;

    /*实现任意日期的付费率统计*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity customPayRate(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.customPayRateTime(statisticsRequest);
    }

    /*实现全部的付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity allPayRate(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.allPayRate(statisticsRequest);
    }

    /*实现任意日期的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/arpu", method = RequestMethod.POST)
    public ResponseEntity customArpu(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.customArpuTime(statisticsRequest);
    }

    /*实现全部的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/arpu/all", method = RequestMethod.POST)
    public ResponseEntity allArpu(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.allArpu(statisticsRequest);
    }

    /*实现任意日期的ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/arppu", method = RequestMethod.POST)
    public ResponseEntity customArppu(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.customArppuTime(statisticsRequest);
    }

    /*实现全部ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/arppu/all", method = RequestMethod.POST)
    public ResponseEntity allArppu(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.allArppu(statisticsRequest);
    }

    /*实现任意日期支付玩家统计*/
    @ResponseBody
    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public ResponseEntity customPayingPlayers(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.customPlayersTime(statisticsRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/players/all", method = RequestMethod.POST)
    public ResponseEntity allPayingPlayers(@RequestBody StatisticsRequest statisticsRequest) {
        return payService.allPlayers(statisticsRequest);
    }

}
