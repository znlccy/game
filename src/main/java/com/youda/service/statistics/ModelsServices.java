package com.youda.service.statistics;

import com.youda.request.statistics.StatisticsRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Chencongye
 * @Date 2018/1/17 0:47
 * @Version 1.0.0
 * @Instructions 机型服务类接口
 */

public interface ModelsServices {

    /*定义自定义日期查询机型统计*/
    ResponseEntity customModels(StatisticsRequest statisticsRequest);

    /*定义查询全部机型统计*/
    ResponseEntity allModels(StatisticsRequest statisticsRequest);
}


