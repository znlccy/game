package com.youda.controller.statistics;

import com.youda.service.statistics.IncomeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/20 14:49
 * @Version 1.0.0
 * @Instructions 定义收入统计控制器
 */

@RestController
@RequestMapping(value = "/admin/income")
public class IncomeController {

    /*实现收入统计服务接口的自动依赖注入*/
    @Autowired
    private IncomeService incomeStatisticsService;

    /*实现今天的收入统计*/
    @ResponseBody
    @RequestMapping(value = "/todayincome", method = RequestMethod.GET)
    public ResponseEntity todayIncomeStatistics() {
        return incomeStatisticsService.todayIncomeStatistics();
    }


    /*实现昨天的收入统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodayincome", method = RequestMethod.GET)
    public ResponseEntity yestodayIncomeStatistics() {
        return incomeStatisticsService.yestodayIncomeStatistics();
    }


    /*实现一周的收入统计*/
    @ResponseBody
    @RequestMapping(value = "/weekincome", method = RequestMethod.GET)
    public ResponseEntity aWeekIncomeStatistics() {
        return incomeStatisticsService.aWeekIncomeStatistics();
    }


    /*实现一个月的收入统计*/
    @ResponseBody
    @RequestMapping(value = "/monthincome", method = RequestMethod.GET)
    public ResponseEntity aMonthIncomeStatistics() {
        return incomeStatisticsService.aMonthIncomeStatistics();
    }


    /*实现自定义日期的收入统计*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity customIncomeStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return incomeStatisticsService.customTime(beginTime, endTime);
    }


    /*实现全部的收入统计*/
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity allIncomeStatistics() {
        return incomeStatisticsService.all();
    }

}
