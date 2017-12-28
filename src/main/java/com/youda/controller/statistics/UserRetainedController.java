package com.youda.controller.statistics;

import com.youda.request.statistics.UserStatisticsRequest;
import com.youda.service.statistics.UserRetainedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Chencongye
 * @Date 2017/12/15 13:56
 * @Version 1.0.0
 * @Instructions 实现用户留存的统计
 */

@RestController
@RequestMapping(value = "/statistics/user/retained")
public class UserRetainedController {

    /*实现用户留存统计服务的自动依赖注入*/
    @Autowired
    private UserRetainedService userRetainedStatisticsService;

    /*实现自定义日期用户留存统计*/
    @ResponseBody
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity customDateRetained(@RequestBody UserStatisticsRequest userStatisticsRequest) {
        return userRetainedStatisticsService.customTime(userStatisticsRequest);
    }

    /*实现所有平台用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity allPlatformUserRetainedStatistics(@RequestBody UserStatisticsRequest userStatisticsRequest) {
        return userRetainedStatisticsService.all(userStatisticsRequest);
    }
}
