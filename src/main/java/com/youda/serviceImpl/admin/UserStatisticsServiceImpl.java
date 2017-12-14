package com.youda.serviceImpl.admin;

import com.youda.dao.admin.UserStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.UserStatisticsResponse;
import com.youda.service.admin.UserStatisticsService;
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
public class UserStatisticsServiceImpl implements UserStatisticsService {

    /*实现用户统计的自动依赖注入*/
    @Autowired
    UserStatisticsMapper userStatisticsMapper;

    @Override
    public ResponseEntity newUserStatistics() {
        List<UserStatisticsResponse> userStatisticsResponses = userStatisticsMapper.NearlyAMonthNewUserStatistics();
        return ResponseStatusCode.putOrGetSuccess(userStatisticsResponses);
    }

    @Override
    public ResponseEntity newEquipmentStatistics() {
        return null;
    }

    @Override
    public ResponseEntity everyDayNewUserStatistics() {
        return null;
    }

    @Override
    public ResponseEntity everyWeekNewUserStatistics() {
        return null;
    }

    @Override
    public ResponseEntity everyMonthNewUserStatistics() {
        return null;
    }

    @Override
    public ResponseEntity dengesActiveStatistics() {
        return null;
    }

    @Override
    public ResponseEntity dengesPaymentStatistics() {
        return null;
    }

    @Override
    public ResponseEntity dengesIncomeStatistics() {
        return null;
    }

    @Override
    public ResponseEntity newUsersRetained() {
        return null;
    }
}
