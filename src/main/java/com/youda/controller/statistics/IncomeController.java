package com.youda.controller.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.service.statistics.IncomeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Chencongye
 * @Date 2017/12/20 14:49
 * @Version 1.0.0
 * @Instructions 定义收入统计控制器
 */

@RestController
@RequestMapping(value = "/statistics/income")
public class IncomeController {

    /*实现收入统计服务接口的自动依赖注入*/
    @Autowired
    private IncomeService incomeService;

    /*实现自定义日期的收入统计*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity customIncome(@RequestBody StatisticsRequest statisticsRequest) {
        return incomeService.customTime(statisticsRequest);
    }


    /*实现全部的收入统计*/
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity allIncome(@RequestBody StatisticsRequest statisticsRequest) {
        return incomeService.all(statisticsRequest);
    }

}
