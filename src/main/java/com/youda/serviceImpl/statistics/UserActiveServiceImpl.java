package com.youda.serviceImpl.statistics;

import com.youda.dao.admin.UserActiveStatisticsMapper;
import com.youda.request.admin.UserStatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.UserActiveStatisticsResponse;
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
    private UserActiveStatisticsMapper userActiveStatisticsMapper;

    /*实现自定义活跃用户统计*/
    @Override
    public ResponseEntity customTime(UserStatisticsRequest userStatisticsRequest) {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.customDateActiveUserStatistics(userStatisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

    /*实现所有平台活跃用户统计*/
    @Override
    public ResponseEntity all(UserStatisticsRequest userStatisticsRequest) {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.allPlatformActiveUserStatistics(userStatisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

}
