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
    @RequestMapping(value = "/todayincome" ,method = RequestMethod.GET)
    public ResponseEntity todayIncomeStatistics() {
        return equipmentStatisticsService.todayEquipmentStatistics();
    };

    /*实现昨天的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodayincome" ,method = RequestMethod.GET)
    public ResponseEntity yestodayIncomeStatistics() {
        return equipmentStatisticsService.yestodayEquipmentStatistics();
    };

    /*实现一周的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/weekincome" ,method = RequestMethod.GET)
    public ResponseEntity aWeekIncomeStatistics() {
        return equipmentStatisticsService.aWeekEquipmentStatistics();
    };

    /*实现一个月的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/monthincome" ,method = RequestMethod.GET)
    public ResponseEntity aMonthIncomeStatistics() {
        return equipmentStatisticsService.aMonthEquipmentStatistics();
    };

    /*实现自定义日期的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/customincome" ,method = RequestMethod.GET)
    public ResponseEntity customIncomeStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return equipmentStatisticsService.customEquipmentStatistics(beginTime, endTime);
    };

    /*实现全部的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/allincome" ,method = RequestMethod.GET)
    public ResponseEntity allIncomeStatistics() {
        return equipmentStatisticsService.allEquipmentStatistics();
    };

}
