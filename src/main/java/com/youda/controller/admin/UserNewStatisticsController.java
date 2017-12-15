package com.youda.controller.admin;

import com.youda.service.admin.UserNewStatisticsService;
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
    UserNewStatisticsService userStatisticsService;

    /*新增用户统计，当日新增加的玩家账户数*/
    @RequestMapping(value = "/nealymonthuser",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity nearlyAMonthNewUserStatistics() {
        return userStatisticsService.nearlyAMonthNewUserStatistics();
    }

    /*今日用户统计*/
    @RequestMapping(value = "/todayuser",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity todayNewUserStatistics() {
        return null;
    }

    /*昨日用户统计*/
    @RequestMapping(value = "/yestodayuser",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity yestodayNewUserStatistics() {
        return null;
    }

    /*周增用户统计*/
    @RequestMapping(value = "/everyweekuser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity everyWeekNewUserStatistics() {
        return userStatisticsService.everyWeekNewUserStatistics();
    }

    /*月增用户统计*/
    @ResponseBody
    @RequestMapping(value = "/everymonthuser", method = RequestMethod.GET)
    public ResponseEntity everyMonthNewUserStatistics() {
        return userStatisticsService.nearlyAMonthNewUserStatistics();
    }

}
