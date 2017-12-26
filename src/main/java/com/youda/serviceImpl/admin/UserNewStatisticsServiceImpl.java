package com.youda.serviceImpl.admin;

import com.youda.dao.admin.UserNewStatisticsMapper;
import com.youda.request.admin.NewUserStatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.UserNewStatisticsResponse;
import com.youda.service.admin.UserNewStatisticsService;
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
public class UserNewStatisticsServiceImpl implements UserNewStatisticsService {

    /*实现用户统计的自动依赖注入*/
    @Autowired
    UserNewStatisticsMapper userStatisticsMapper;

    /*实现自定义日期新增用户统计*/
    @Override
    public ResponseEntity customDateNewUserStatistics(NewUserStatisticsRequest newUserStatisticsRequest) {

       List<UserNewStatisticsResponse> userNewStatisticsRespons = userStatisticsMapper.cudtomDateNewUserStatistics(newUserStatisticsRequest);
       return ResponseStatusCode.putOrGetSuccess(userNewStatisticsRespons);
    }

    /*实现所用新增用户统计*/
    @Override
    public ResponseEntity allNewUserStatistics() {
        List<UserNewStatisticsResponse> userNewStatisticsRespons = userStatisticsMapper.allNewUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userNewStatisticsRespons);
    }
}
