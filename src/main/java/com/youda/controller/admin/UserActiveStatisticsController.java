package com.youda.controller.admin;

import com.youda.service.admin.UserActiveStatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/19 14:56
 * @Version 1.0.0
 * @Instructions 定义用户活跃度统计的控制器
 */

@RestController
@RequestMapping(value = "/admin")
public class UserActiveStatisticsController {

    /*实现用户活跃度服务的自动依赖注入*/
    @Autowired
    private UserActiveStatisticsService userActiveStatisticsService;

    /*定义今天活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/todayactiveuser", method = RequestMethod.GET)
    public ResponseEntity todayActiveUserStatistics() {
        return userActiveStatisticsService.todayActiveUserStatistics();
    };

    /*定义昨天活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodayactiveuser", method = RequestMethod.GET)
    public ResponseEntity yestodayActiveUserStatistics() {
        return userActiveStatisticsService.yestodayActiveUserStatistics();
    };

    /*定义每周活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "weekactiveuser", method = RequestMethod.GET)
    public ResponseEntity aWeekActiveUserStatistics() {
        return userActiveStatisticsService.aWeekActiveUserStatistics();
    };

    /*定义一个月活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/monthactiveuser", method = RequestMethod.GET)
    public ResponseEntity aMonthActiveUserStatistics() {
        return userActiveStatisticsService.aMonthActiveUserStatistics();
    };

    /*定义自选日期活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/customactiveuser", method = RequestMethod.GET)
    public ResponseEntity customDateActiveUserStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return userActiveStatisticsService.customDateActiveUserStatistics(beginTime, endTime);
    };

    /*定义所有活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/allactiveuser", method = RequestMethod.GET)
    public ResponseEntity allActiveUserStatistics() {
        return userActiveStatisticsService.allActiveUserStatistics();
    };

}
