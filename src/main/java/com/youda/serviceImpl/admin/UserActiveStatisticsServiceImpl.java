package com.youda.serviceImpl.admin;

import com.youda.dao.admin.UserActiveStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.UserActiveStatisticsResponse;
import com.youda.service.admin.UserActiveStatisticsService;
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
public class UserActiveStatisticsServiceImpl implements UserActiveStatisticsService {

    /*实现活跃用户Dao层自动依赖注入*/
    @Autowired
    private UserActiveStatisticsMapper userActiveStatisticsMapper;

    /*实现今天活跃用户统计*/
    @Override
    public ResponseEntity todayActiveUserStatistics() {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.todayActiveUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

    /*实现昨天活跃用户统计*/
    @Override
    public ResponseEntity yestodayActiveUserStatistics() {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.yestodayActiveUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

    /*实现一周活跃用户统计*/
    @Override
    public ResponseEntity aWeekActiveUserStatistics() {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.aWeekActiveUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

    /*实现一个月活跃用户统计*/
    @Override
    public ResponseEntity aMonthActiveUserStatistics() {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.aMonthActiveUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

    /*实现自定义活跃用户统计*/
    @Override
    public ResponseEntity customDateActiveUserStatistics(String beginTime, String endTime) {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.customDateActiveUserStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

    /*实现所有活跃用户统计*/
    @Override
    public ResponseEntity allActiveUserStatistics() {
        List<UserActiveStatisticsResponse> userActiveStatisticsResponses = userActiveStatisticsMapper.allActiveUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userActiveStatisticsResponses);
    }

}
