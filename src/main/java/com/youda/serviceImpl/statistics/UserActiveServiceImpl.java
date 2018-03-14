package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.UserActiveMapper;
import com.youda.request.statistics.PeriodRequest;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.PeriodResponse;
import com.youda.response.statistics.UserActiveResponse;
import com.youda.service.statistics.UserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:45
 * @Version 1.0.0
 * @Instructions 实现活跃用户统计服务层接口的实现
 */

@Service
public class UserActiveServiceImpl implements UserActiveService {

    /*实现活跃用户Dao层自动依赖注入*/
    @Autowired
    private UserActiveMapper userActiveMapper;

    /*实现自定义活跃用户统计*/
    @Override
    public ResponseEntity customTime(StatisticsRequest statisticsRequest) {
        List<UserActiveResponse> userActiveRespons = userActiveMapper.customTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userActiveRespons);
    }

    /*实现所有平台活跃用户统计*/
    @Override
    public ResponseEntity all(StatisticsRequest statisticsRequest) {
        List<UserActiveResponse> userActiveRespons = userActiveMapper.all(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userActiveRespons);
    }

    /*实现一天24小时用户统计*/
    @Override
    public ResponseEntity period(PeriodRequest periodRequest) {
        List<PeriodResponse> periodResponses = userActiveMapper.periodStatistics(periodRequest);
        return ResponseStatusCode.putOrGetSuccess(periodResponses);
    }
}
