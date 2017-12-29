package com.youda.controller.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.service.statistics.EquipmentRetainedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:09
 * @Version 1.0.0
 * @Instructions 实现新增设备统计功能
 */

@RestController
@RequestMapping(value = "/statistics/equipment")
public class EquipmentController {

    /*实现新增设备服务的自动依赖注入*/
    @Autowired
    private EquipmentRetainedService equipmentRetainedService;


    /*实现自定义日期的新增设备统计*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity customNewEquipment(@RequestBody StatisticsRequest statisticsRequest) {
        return equipmentRetainedService.customTime(statisticsRequest);
    };

    /*实现全部的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/all" ,method = RequestMethod.POST)
    public ResponseEntity allNewEquipment(@RequestBody StatisticsRequest statisticsRequest) {
        return equipmentRetainedService.all(statisticsRequest);
    };

}
