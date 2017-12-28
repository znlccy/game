package com.youda.controller.statistics;

import com.youda.service.statistics.EquipmentService;
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
@RequestMapping(value = "/admin/equipment")
public class EquipmentController {

    /*实现新增设备服务的自动依赖注入*/
    @Autowired
    private EquipmentService equipmentService;

    /*实现今天的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/todayretainedequipment" ,method = RequestMethod.GET)
    public ResponseEntity todayNewEquipmentStatistics() {
        return equipmentService.todayEquipmentStatistics();
    };

    /*实现昨天的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/yestodayretainedequipment" ,method = RequestMethod.GET)
    public ResponseEntity yestodayNewEquipmentStatistics() {
        return equipmentService.yestodayEquipmentStatistics();
    };

    /*实现一周的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/weekretainedequipment" ,method = RequestMethod.GET)
    public ResponseEntity aWeekNewEquipmentStatistics() {
        return equipmentService.aWeekEquipmentStatistics();
    };

    /*实现一个月的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/monthretainedequipment" ,method = RequestMethod.GET)
    public ResponseEntity aMonthNewEquipmentStatistics() {
        return equipmentService.aMonthEquipmentStatistics();
    };

    /*实现自定义日期的新增设备统计*/
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity customNewEquipmentStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime) {
        return equipmentService.customTime(beginTime, endTime);
    };

    /*实现全部的新增设备统计*/
    @ResponseBody
    @RequestMapping(value = "/all" ,method = RequestMethod.GET)
    public ResponseEntity allNewEquipmentStatistics() {
        return equipmentService.all();
    };

}
