package com.youda.serviceImpl.statistics;

import com.youda.dao.admin.EquipmentRetainedStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.EquipmentRetainedStatisticsResponse;
import com.youda.service.statistics.EquipmentService;
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
public class EquipmentServiceImpl implements EquipmentService {

    /*实现新增设备Dao层的自动依赖注入*/
    @Autowired
    private EquipmentRetainedStatisticsMapper equipmentRetainedStatisticsMapper;

    /*实现今天的新增设备的统计*/
    @Override
    public ResponseEntity todayEquipmentStatistics() {
        List<EquipmentRetainedStatisticsResponse> equipmentRetainedStatisticsRespons = equipmentRetainedStatisticsMapper.todayEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedStatisticsRespons);
    }

    /*实现昨天的新增设备的统计*/
    @Override
    public ResponseEntity yestodayEquipmentStatistics() {
        List<EquipmentRetainedStatisticsResponse> equipmentRetainedStatisticsRespons = equipmentRetainedStatisticsMapper.yestodayEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedStatisticsRespons);
    }

    /*实现一周的新增设备的统计*/
    @Override
    public ResponseEntity aWeekEquipmentStatistics() {
        List<EquipmentRetainedStatisticsResponse> equipmentRetainedStatisticsRespons = equipmentRetainedStatisticsMapper.aWeekEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedStatisticsRespons);
    }

    /*实现一个月新增设备的统计*/
    @Override
    public ResponseEntity aMonthEquipmentStatistics() {
        List<EquipmentRetainedStatisticsResponse> equipmentRetainedStatisticsRespons = equipmentRetainedStatisticsMapper.aMonthEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedStatisticsRespons);
    }

    /*实现自定义日期新增设备的统计*/
    @Override
    public ResponseEntity customTime(String beginTime,String endTime) {
        List<EquipmentRetainedStatisticsResponse> equipmentRetainedStatisticsRespons = equipmentRetainedStatisticsMapper.customEquipmentStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedStatisticsRespons);
    }

    /*实现全部新增设备的统计*/
    @Override
    public ResponseEntity all() {
        List<EquipmentRetainedStatisticsResponse> equipmentRetainedStatisticsRespons = equipmentRetainedStatisticsMapper.allEquipmentStatistics();
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedStatisticsRespons);
    }
}
