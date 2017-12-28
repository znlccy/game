package com.youda.controller.admin;

import com.youda.request.admin.UserStatisticsRequest;
import com.youda.service.admin.UserActiveStatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*定义自选日期活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/customactiveuser", method = RequestMethod.POST)
    public ResponseEntity customDateActiveUserStatistics(@RequestBody UserStatisticsRequest userStatisticsRequest) {
        return userActiveStatisticsService.customDateActiveUserStatistics(userStatisticsRequest);
    };

    /*定义所有活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/allactiveuser", method = RequestMethod.POST)
    public ResponseEntity allActiveUserStatistics(@RequestBody UserStatisticsRequest userStatisticsRequest) {
        return userActiveStatisticsService.allPlatformActiveUserStatistics(userStatisticsRequest);
    };

}
