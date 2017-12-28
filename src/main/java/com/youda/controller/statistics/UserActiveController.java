package com.youda.controller.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.service.statistics.UserActiveService;
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
@RequestMapping(value = "/statistics/user/active")
public class UserActiveController {

    /*实现用户活跃度服务的自动依赖注入*/
    @Autowired
    private UserActiveService userActiveService;

    /*定义自选日期活跃用户统计*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity customDateActiveUserStatistics(@RequestBody StatisticsRequest statisticsRequest) {
        return userActiveService.customTime(statisticsRequest);
    }

    /*定义所有活跃用户统计*/
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity allActiveUserStatistics(@RequestBody StatisticsRequest statisticsRequest) {
        return userActiveService.all(statisticsRequest);
    }

}
