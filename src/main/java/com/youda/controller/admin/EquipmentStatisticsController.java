package com.youda.controller.admin;

import com.youda.service.admin.EquipmentStatisticsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:09
 * @Version 1.0.0
 * @Instructions 实现新增设备统计功能
 */

@RestController
@RequestMapping(value = "/admin")
public class EquipmentStatisticsController {

    /*实现新增设备服务的自动依赖注入*/
    @Autowired
    private EquipmentStatisticsService equipmentStatisticsService;

    /*实现今天的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/todaynewequipment" ,method = RequestMethod.GET)
    public ResponseEntity todayNewEquipmentStatistics() {
        return equipmentStatisticsService.todayEquipmentStatistics();
    };

    /*实现昨天的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodaynewequipment" ,method = RequestMethod.GET)
    public ResponseEntity yestodayNewEquipmentStatistics() {
        return equipmentStatisticsService.yestodayEquipmentStatistics();
    };

    /*实现一周的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/weeknewequipment" ,method = RequestMethod.GET)
    public ResponseEntity aWeekNewEquipmentStatistics() {
        return equipmentStatisticsService.aWeekEquipmentStatistics();
    };

    /*实现一个月的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/monthnewequipment" ,method = RequestMethod.GET)
    public ResponseEntity aMonthNewEquipmentStatistics() {
        return equipmentStatisticsService.aMonthEquipmentStatistics();
    };

    /*实现自定义日期的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/customnewequipment" ,method = RequestMethod.GET)
    public ResponseEntity customNewEquipmentStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return equipmentStatisticsService.customEquipmentStatistics(beginTime, endTime);
    };

    /*实现全部的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/allnewequipment" ,method = RequestMethod.GET)
    public ResponseEntity allNewEquipmentStatistics() {
        return equipmentStatisticsService.allEquipmentStatistics();
    };

}
