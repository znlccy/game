package com.youda.service.statistics;

import com.youda.request.statistics.StatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2017/12/21 14:12
 * @Version 1.0.0
 * @Instructions 实现新增设备统计服务接口
 */

public interface EquipmentService {


    /*实现自定义日期的新增设备的统计*/
    ResponseEntity customTime(StatisticsRequest statisticsRequest);

    /*实现全部的新增设备的统计*/
    ResponseEntity all( StatisticsRequest statisticsRequest);

}

