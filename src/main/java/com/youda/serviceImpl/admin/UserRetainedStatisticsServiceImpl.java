package com.youda.serviceImpl.admin;

import com.youda.dao.admin.UserRetainedStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.UserRetainedStatisticsResponse;
import com.youda.service.admin.UserRetainedStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:48
 * @Version 1.0.0
 * @Instructions 实现用户留存统计的服务层类
 */

@Service
public class UserRetainedStatisticsServiceImpl implements UserRetainedStatisticsService {

    /*实现用户留存统计的自动依赖注入*/
    @Autowired
    private UserRetainedStatisticsMapper userRetainedStatisticsMapper;

    /*实现今天用户留存的统计功能*/
    @Override
    public ResponseEntity todayUserRetainedStatistics() {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.todayUserRetainedStatistics();
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }

    /*实现昨天的用户留存统计功能*/
    @Override
    public ResponseEntity yestodayUserRetainedStatistics() {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.yestodayUserRetainedStatistics();
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }

    /*实现一周的用户留存统计功能*/
    @Override
    public ResponseEntity aWeekUserRetainedStatistics() {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.aWeekUserRetainedStatistics();
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }

    /*实现一个月的用户留存统计的功能*/
    @Override
    public ResponseEntity aMonthUserRetainedStatistics() {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.aMonthUserRetainedStatistics();
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }

    /*实现自定义日期用户的留存统计的功能*/
    @Override
    public ResponseEntity customDateRetainedStatistics(String beginTime, String endTime) {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.customDateRetainedStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }

    /*实现所有的用户留存统计的功能*/
    @Override
    public ResponseEntity allUserRetainedStatisticsStatistics() {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.allUserRetainedStatisticsStatistics();
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }
}
