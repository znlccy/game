package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.EquipmentRetainedMapper;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.EquipmentRetainedResponse;
import com.youda.service.statistics.EquipmentRetainedService;
import org.apache.ibatis.annotations.Param;
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
public class EquipmentRetainedServiceImpl implements EquipmentRetainedService {

    /*实现新增设备Dao层的自动依赖注入*/
    @Autowired
    private EquipmentRetainedMapper equipmentRetainedMapper;

    /*实现自定义日期新增设备的统计*/
    @Override
    public ResponseEntity customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest) {
        List<EquipmentRetainedResponse> equipmentRetainedResponses = equipmentRetainedMapper.customTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedResponses);
    }

    /*实现全部新增设备的统计*/
    @Override
    public ResponseEntity all(@Param("statisticsRequest") StatisticsRequest statisticsRequest) {
        List<EquipmentRetainedResponse> equipmentRetainedResponses = equipmentRetainedMapper.all(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(equipmentRetainedResponses);
    }
}
