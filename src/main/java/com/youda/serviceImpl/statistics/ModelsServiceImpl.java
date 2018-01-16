package com.youda.serviceImpl.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.service.statistics.ModelsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Author Chencongye
 * @Date 2018/1/17 0:58
 * @Version 1.0.0
 * @Instructions
 */

@Service
public class ModelsServiceImpl implements ModelsServices {


    @Override
    public ResponseEntity customModels(StatisticsRequest statisticsRequest) {
        return null;
    }

    @Override
    public ResponseEntity allModels(StatisticsRequest statisticsRequest) {
        return null;
    }
}
