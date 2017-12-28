package com.youda.controller.admin;

import com.youda.request.admin.UserStatisticsRequest;
import com.youda.service.admin.UserNewStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*实现自定义日期查询统计*/
    @ResponseBody
    @RequestMapping(value = "/customnewuser", method = RequestMethod.POST)
    public ResponseEntity customDatesNewUserStatistics(@RequestBody UserStatisticsRequest userStatisticsRequest) {
        return userNewStatisticsService.customDateNewUserStatistics(userStatisticsRequest);
    }

    /*实现全部新增用户查询统计*/
    @ResponseBody
    @RequestMapping(value = "/allnewuser", method = RequestMethod.POST)
    public ResponseEntity allPlatformNewUserStatistics(@RequestBody UserStatisticsRequest userStatisticsRequest) {
        return userNewStatisticsService.allPlatformNewUserStatistics(userStatisticsRequest);
    }

}
