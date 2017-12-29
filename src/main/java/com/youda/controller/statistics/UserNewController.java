package com.youda.controller.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.service.statistics.UserNewService;
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
@RequestMapping(value = "/statistics/user/new")
public class UserNewController {

    @Autowired
    UserNewService userNewService;

    /*实现自定义日期查询统计*/
    @ResponseBody
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity customNew(@RequestBody StatisticsRequest statisticsRequest) {
        return userNewService.customTime(statisticsRequest);
    }

    /*实现全部新增用户查询统计*/
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity allNew(@RequestBody StatisticsRequest statisticsRequest) {
        return userNewService.all(statisticsRequest);
    }

}
