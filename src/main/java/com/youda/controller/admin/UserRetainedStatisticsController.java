package com.youda.controller.admin;

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

    /*实现今天的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "todayretained",method = RequestMethod.GET)
    public ResponseEntity todayUserRetainedStatistics() {
        return null;
    }

    /*实现昨天的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "yestodayretained",method = RequestMethod.GET)
    public ResponseEntity yestodayUserRetainedStatistics() {
        return null;
    }

    /*实现一周的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "weekretained",method = RequestMethod.GET)
    public ResponseEntity aWeekUserRetained() {
        return null;
    }

    /*实现一月的用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "monthretained",method = RequestMethod.GET)
    public ResponseEntity aMonthUserRetained() {
        return null;
    }

    /*实现自定义日期用户留存统计*/
    @ResponseBody
    @RequestMapping(value = "/customdate",method = RequestMethod.GET)
    public ResponseEntity definitionDateRetained() {
        return null;
    }

}
