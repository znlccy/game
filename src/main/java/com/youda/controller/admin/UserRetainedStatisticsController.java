package com.youda.controller.admin;

import com.youda.service.admin.UserRetainedStatisticsService;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping(value = "/admin")
public class UserRetainedStatisticsController {

    /*实现用户留存统计服务的自动依赖注入*/
    @Autowired
    private UserRetainedStatisticsService userRetainedStatisticsService;

    /*实现今天的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "todayretaineduser",method = RequestMethod.GET)
    public ResponseEntity todayUserRetainedStatistics() {
        return userRetainedStatisticsService.todayUserRetainedStatistics();
    }

    /*实现昨天的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "yestodayretaineduser",method = RequestMethod.GET)
    public ResponseEntity yestodayUserRetainedStatistics() {
        return userRetainedStatisticsService.yestodayUserRetainedStatistics();
    }

    /*实现一周的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "weekretaineduser",method = RequestMethod.GET)
    public ResponseEntity aWeekUserRetained() {
        return userRetainedStatisticsService.aWeekUserRetainedStatistics();
    }

    /*实现一月的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "monthretaineduser",method = RequestMethod.GET)
    public ResponseEntity aMonthUserRetained() {
        return userRetainedStatisticsService.aMonthUserRetainedStatistics();
    }

    /*实现自定义日期用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "/customretaineduser",method = RequestMethod.GET)
    public ResponseEntity customDateRetained(@Param("beginTime") String beginTime,@Param("endTime") String endTime) {
        return userRetainedStatisticsService.customDateRetainedStatistics(beginTime, endTime);
    }

    @ResponseBody
    @RequestMapping(value = "/allretaineduser", method = RequestMethod.GET )
    public ResponseEntity allUserRetainedStatistics() {
        return userRetainedStatisticsService.allUserRetainedStatisticsStatistics();
    }

}
