package com.youda.serviceImpl.admin;

import com.youda.dao.admin.EquipmentStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.EquipmentStatisticsResponse;
import com.youda.service.admin.EquipmentStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 16:01
 * @Version 1.0.0
 * @Instructions 实现新增设备服务层接口
 */
@Service
public class EquipmentStatisticsServiceImpl implements EquipmentStatisticsService {

    /*实现新增设备Dao层的自动依赖注入*/
    @Autowired
    private EquipmentStatisticsMapper equipmentStatisticsMapper;

    /*实现今天的新增设备的统计*/
    @Override
    public ResponseEntity todayEquipmentStatistics() {
        List<EquipmentStatisticsResponse> equipmentStatisticsResponses = equipmentStatisticsMapper.todayEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentStatisticsResponses);
    }

    /*实现昨天的新增设备的统计*/
    @Override
    public ResponseEntity yestodayEquipmentStatistics() {
        List<EquipmentStatisticsResponse> equipmentStatisticsResponses = equipmentStatisticsMapper.yestodayEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentStatisticsResponses);
    }

    /*实现一周的新增设备的统计*/
    @Override
    public ResponseEntity aWeekEquipmentStatistics() {
        List<EquipmentStatisticsResponse> equipmentStatisticsResponses = equipmentStatisticsMapper.aWeekEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentStatisticsResponses);
    }

    /*实现一个月新增设备的统计*/
    @Override
    public ResponseEntity aMonthEquipmentStatistics() {
        List<EquipmentStatisticsResponse> equipmentStatisticsResponses = equipmentStatisticsMapper.aMonthEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentStatisticsResponses);
    }

    /*实现自定义日期新增设备的统计*/
    @Override
    public ResponseEntity customEquipmentStatistics(String beginTime,String endTime) {
        List<EquipmentStatisticsResponse> equipmentStatisticsResponses = equipmentStatisticsMapper.customEquipmentStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(equipmentStatisticsResponses);
    }

    /*实现全部新增设备的统计*/
    @Override
    public ResponseEntity allEquipmentStatistics() {
        List<EquipmentStatisticsResponse> equipmentStatisticsResponses = equipmentStatisticsMapper.allEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentStatisticsResponses);
    }
}
