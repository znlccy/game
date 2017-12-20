package com.youda.serviceImpl.admin;

import com.youda.dao.admin.IncomeStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.IncomeStatisticsResponse;
import com.youda.service.admin.IncomeStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/20 15:06
 * @Version 1.0.0
 * @Instructions 实现收入统计服务类接口
 */

@Service
public class IncomeStatisticsServiceImpl implements IncomeStatisticsService {

    /*实现收入统计Dao层自动依赖注入*/
    @Autowired
    private IncomeStatisticsMapper incomeStatisticsMapper;

    /*实现今天的收入统计*/
    @Override
    public ResponseEntity todayIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeStatisticsMapper.todayIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现昨天的收入统计*/
    @Override
    public ResponseEntity yestodayIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeStatisticsMapper.yestodayIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现一周的收入统计*/
    @Override
    public ResponseEntity aWeekIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeStatisticsMapper.aWeekIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现一个月的收入统计*/
    @Override
    public ResponseEntity aMonthIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeStatisticsMapper.aMonthIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现自定义日期收入统计*/
    @Override
    public ResponseEntity customIncomeStatistics(String beginTime, String endTime) {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeStatisticsMapper.customIncomeStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现全部收入统计*/
    @Override
    public ResponseEntity allIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeStatisticsMapper.allIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }
}
