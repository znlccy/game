package com.youda.controller.admin;

import com.youda.service.admin.UserNewStatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/13 16:08
 * @Version 1.0.0
 * @Instructions 实现用户统计功能
 */

@RestController
@RequestMapping(value = "/admin")
public class UserNewStatisticsController {

    @Autowired
    UserNewStatisticsService userNewStatisticsService;

    /*新增用户统计，当日新增加的玩家账户数*/
    @RequestMapping(value = "/monthnewuser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity aMonthNewUserStatistics() {
        return userNewStatisticsService.nearlyAMonthNewUserStatistics();
    }

    /*今日用户统计*/
    @RequestMapping(value = "/todaynewuser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity todayNewUserStatistics() {
        return userNewStatisticsService.todayNewUserStatistics();
    }

    /*昨日用户统计*/
    @RequestMapping(value = "/yestodaynewuser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity yestodayNewUserStatistics() {
        return userNewStatisticsService.yestodayNewUserStatistics();
    }

    /*周增用户统计*/
    @RequestMapping(value = "/weeknewuser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity everyWeekNewUserStatistics() {
        return userNewStatisticsService.everyWeekNewUserStatistics();
    }

    /*实现自定义日期查询统计*/
    @ResponseBody
    @RequestMapping(value = "customnewuser", method = RequestMethod.GET)
    public ResponseEntity customDatesNewUserStatistics(@Param("betginTime") String beginTime,@Param("endTime") String endTime) {
        return userNewStatisticsService.customDateNewUserStatistics(beginTime, endTime);
    }

    /*实现全部新增用户查询统计*/
    @ResponseBody
    @RequestMapping(value = "/allnewuser", method = RequestMethod.GET)
    public ResponseEntity allNewUserStatistics() {
        return userNewStatisticsService.allNewUserStatistics();
    }

}
