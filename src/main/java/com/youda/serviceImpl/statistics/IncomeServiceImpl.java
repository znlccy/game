package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.IncomeMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.IncomeResponse;
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
        List<IncomeResponse> incomeRespons = incomeMapper.todayIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }

    /*实现昨天的收入统计*/
    @Override
    public ResponseEntity yestodayIncomeStatistics() {
        List<IncomeResponse> incomeRespons = incomeMapper.yestodayIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }

    /*实现一周的收入统计*/
    @Override
    public ResponseEntity aWeekIncomeStatistics() {
        List<IncomeResponse> incomeRespons = incomeMapper.aWeekIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }

    /*实现一个月的收入统计*/
    @Override
    public ResponseEntity aMonthIncomeStatistics() {
        List<IncomeResponse> incomeRespons = incomeMapper.aMonthIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }

    /*实现自定义日期收入统计*/
    @Override
    public ResponseEntity customTime(String beginTime, String endTime) {
        List<IncomeResponse> incomeRespons = incomeMapper.customIncomeStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }

    /*实现全部收入统计*/
    @Override
    public ResponseEntity all() {
        List<IncomeResponse> incomeRespons = incomeMapper.allIncomeStatistics();
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }
}
