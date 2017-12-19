package com.youda.controller.admin;

import com.youda.service.admin.PayStatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/13 16:10
 * @Version 1.0.0
 * @Instructions 实现付费和收益统计
 */

@RestController
@RequestMapping(value = "/admin")
public class PayStatisticsController {

    /*实现支付统计的自动依赖注入*/
    @Autowired
    PayStatisticsService payStatisticsService;

    /*实现今日付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/todaypayrate", method = RequestMethod.GET)
    public ResponseEntity todayPayRateStatistics() {
        return payStatisticsService.todayPayRateStatistics();
    }

    /*实现昨日付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodaypayrate" ,method = RequestMethod.GET)
    public ResponseEntity yestodayPayRateStatistics() {
        return payStatisticsService.yestodayPayRateStatistics();
    }

    /*实现一周付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/weekpayrate", method = RequestMethod.GET)
    public ResponseEntity aWeekPayRateStatistics() {
        return payStatisticsService.aWeekPayRateStatistics();
    }

    /*实现一个月的付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/monthpayrate",method = RequestMethod.GET)
    public ResponseEntity aMonthPayRateStatistics() {
        return payStatisticsService.aMonthPayRateStatistics();
    }

    /*实现任意日期的付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/custompayrate", method = RequestMethod.GET)
    public ResponseEntity customPayRateStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime) {
        return payStatisticsService.customPayRateStatistics(beginTime,endTime);
    }

    /*实现全部的付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/allpayrate",method = RequestMethod.GET)
    public ResponseEntity allPayRateStatistics() {
        return payStatisticsService.allPayRateStatistics();
    }

    /*实现今天ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/todayarpu",method = RequestMethod.GET)
    public ResponseEntity todayArpuStatistics() {
        return payStatisticsService.todayArpuStatistics();
    }

    /*实现昨天的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodayarpu", method = RequestMethod.GET)
    public ResponseEntity yestodayArpuStatistics() {
        return payStatisticsService.yestodayArpuStatistics();
    }

    /*实现一周的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/weekarpu", method = RequestMethod.GET)
    public ResponseEntity aWeekArpuStatistics() {
        return payStatisticsService.aWeekArpuStatistics();
    }

    /*实现一个月的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/montharpu", method = RequestMethod.GET)
    public ResponseEntity aMonthArpuStatistics() {
        return payStatisticsService.aMonthArpuStatistics();
    }

    /*实现任意日期的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/customarpu",method = RequestMethod.GET)
    public ResponseEntity customArpuStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime) {
        return payStatisticsService.customArpuStatistics(beginTime,endTime);
    }

    /*实现全部的ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/allarpu", method = RequestMethod.GET)
    public ResponseEntity allArpuStatistics() {
        return payStatisticsService.allArpuStatistics();
    }

    /*实现今天的ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/todayarppu",method = RequestMethod.GET)
    public ResponseEntity todayArppuStatistics() {
        return payStatisticsService.todayArppuStatistics();
    }

    /*实现昨天的ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodayarppu",method = RequestMethod.GET)
    public ResponseEntity yestodayArppuStatistics() {
        return payStatisticsService.yestodayArppuStatistics();
    }

    /*实现一周ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/weekarppu",method = RequestMethod.GET)
    public ResponseEntity aWeekArppuStatistics() {
        return payStatisticsService.aWeekArppuStatistics();
    }

    /*实现一个月的ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/montharppu", method = RequestMethod.GET)
    public ResponseEntity aMonthArppuStatistics() {
        return payStatisticsService.aMonthArppuStatistics();
    }

    /*实现任意日期的ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/customarppu", method = RequestMethod.GET)
    public ResponseEntity customArppuStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return payStatisticsService.customArppuStatistics(beginTime, endTime);
    }

    /*实现全部ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/allarppu", method = RequestMethod.GET)
    public ResponseEntity allArppuStatistics() {
        return payStatisticsService.allArppuStatistics();
    }

    /*实现今天支付玩家统计*/
    @ResponseBody
    @RequestMapping(value = "/todaypayplayers", method = RequestMethod.GET)
    public ResponseEntity todayPayingPlayersStatistics() {
        return payStatisticsService.todayPayingPlayersStatistics();
    }

    /*实现昨天支付玩家统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodaypayplayers" ,method = RequestMethod.GET)
    public ResponseEntity yestodayPayingPlayersStatistics() {
        return payStatisticsService.yestodayPayingPlayersStatistics();
    }

    /*实现一周支付玩家统计*/
    @ResponseBody
    @RequestMapping(value = "/weekpayplayers", method = RequestMethod.GET)
    public ResponseEntity aWeekPayingPlayersStatistics() {
        return payStatisticsService.aWeekPayingPlayersStatistics();
    }

    /*实现一个月支付玩家统计*/
    @ResponseBody
    @RequestMapping(value = "/monthpayplayers", method = RequestMethod.GET)
    public ResponseEntity aMonthPayingPlayersStatistics() {
        return payStatisticsService.aMonthPayingPlayersStatistics();
    }

    /*实现任意日期支付玩家统计*/
    @ResponseBody
    @RequestMapping(value = "/custompayplayers" ,method = RequestMethod.GET)
    public ResponseEntity customPayingPlayersStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime) {
        return payStatisticsService.customPayingPlayersStatistics(beginTime,endTime);
    }

    @ResponseBody
    @RequestMapping(value = "/allpayplayers", method = RequestMethod.GET)
    public ResponseEntity allPayingPlayersStatistics() {
        return payStatisticsService.allPayingPlayersStatistics();
    }

}
