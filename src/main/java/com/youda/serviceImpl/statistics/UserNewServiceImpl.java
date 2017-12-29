package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.UserNewMapper;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.UserNewResponse;
import com.youda.service.statistics.UserNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/14 13:05
 * @Version 1.0.0
 * @Instructions 实现用户数据统计
 */

@Service
public class UserNewServiceImpl implements UserNewService {

    /*实现用户统计的自动依赖注入*/
    @Autowired
    UserNewMapper userStatisticsMapper;

    /*实现自定义日期新增用户统计*/
    @Override
    public ResponseEntity customTime(StatisticsRequest statisticsRequest) {

        List<UserNewResponse> userNewResponses = userStatisticsMapper.cudtomTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userNewResponses);
    }

    /*实现所用新增用户统计*/
    @Override
    public ResponseEntity all(StatisticsRequest statisticsRequest) {
        List<UserNewResponse> userNewResponses = userStatisticsMapper.all(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userNewResponses);
    }
}
