package com.youda.serviceImpl.statistics;

import com.youda.dao.admin.UserRetainedStatisticsMapper;
import com.youda.request.admin.UserStatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.UserRetainedStatisticsResponse;
import com.youda.service.statistics.UserRetainedService;
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
public class UserRetainedServiceImpl implements UserRetainedService {

    /*实现用户留存统计的自动依赖注入*/
    @Autowired
    private UserRetainedStatisticsMapper userRetainedStatisticsMapper;


    /*实现自定义日期用户的留存统计的功能*/
    @Override
    public ResponseEntity customTime(UserStatisticsRequest userStatisticsRequest) {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.customDateRetainedStatistics(userStatisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }

    /*实现所有平台的用户留存统计的功能*/
    @Override
    public ResponseEntity all(UserStatisticsRequest userStatisticsRequest) {
        List<UserRetainedStatisticsResponse> userRetainedStatisticsResponses = userRetainedStatisticsMapper.allPlatformUserRetainedStatistics(userStatisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userRetainedStatisticsResponses);
    }
}
