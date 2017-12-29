package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.IncomeMapper;
import com.youda.request.statistics.StatisticsRequest;
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


    /*实现自定义日期收入统计*/
    @Override
    public ResponseEntity customTime(StatisticsRequest statisticsRequest) {
        List<IncomeResponse> incomeRespons = incomeMapper.customTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }

    /*实现全部收入统计*/
    @Override
    public ResponseEntity all(StatisticsRequest statisticsRequest) {
        List<IncomeResponse> incomeRespons = incomeMapper.all(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(incomeRespons);
    }
}
