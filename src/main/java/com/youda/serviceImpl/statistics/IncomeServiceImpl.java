package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.IncomeMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.IncomeStatisticsResponse;
import com.youda.service.statistics.IncomeService;
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
public class IncomeServiceImpl implements IncomeService {

    /*实现收入统计Dao层自动依赖注入*/
    @Autowired
    private IncomeMapper incomeMapper;

    /*实现今天的收入统计*/
    @Override
    public ResponseEntity todayIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeMapper.todayIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现昨天的收入统计*/
    @Override
    public ResponseEntity yestodayIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeMapper.yestodayIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现一周的收入统计*/
    @Override
    public ResponseEntity aWeekIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeMapper.aWeekIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现一个月的收入统计*/
    @Override
    public ResponseEntity aMonthIncomeStatistics() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeMapper.aMonthIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现自定义日期收入统计*/
    @Override
    public ResponseEntity customTime(String beginTime, String endTime) {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeMapper.customIncomeStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }

    /*实现全部收入统计*/
    @Override
    public ResponseEntity all() {
        List<IncomeStatisticsResponse> incomeStatisticsResponses = incomeMapper.allIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeStatisticsResponses);
    }
}
