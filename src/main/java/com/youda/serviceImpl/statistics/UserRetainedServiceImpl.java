package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.UserRetainedMapper;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.UserRetainedResponse;
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
    private UserRetainedMapper userRetainedMapper;


    /*实现自定义日期用户的留存统计的功能*/
    @Override
    public ResponseEntity customTime(StatisticsRequest statisticsRequest) {
        List<UserRetainedResponse> userRetainedRespons = userRetainedMapper.customTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userRetainedRespons);
    }

    /*实现所有平台的用户留存统计的功能*/
    @Override
    public ResponseEntity all(StatisticsRequest statisticsRequest) {
        List<UserRetainedResponse> userRetainedRespons = userRetainedMapper.all(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(userRetainedRespons);
    }
}
