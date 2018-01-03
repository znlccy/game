package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.PayMapper;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.PayResponse;
import com.youda.response.statistics.StatisticsRateResponse;
import com.youda.service.statistics.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:47
 * @Version 1.0.0
 * @Instructions 实现付费统计服务层
 */

@Service
public class PayServiceImpl implements PayService {

    /*实现支付统计Dao层的自动依赖注入*/
    @Autowired
    private PayMapper payMapper;

    /*实现任意日期支付率统计*/
    @Override
    public ResponseEntity customPayRateTime(StatisticsRequest statisticsRequest) {
        List<StatisticsRateResponse> payRespons = payMapper.customPayRateTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部支付率统计*/
    @Override
    public ResponseEntity allPayRate(StatisticsRequest statisticsRequest) {
        List<StatisticsRateResponse> payRespons = payMapper.allPayRate(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期的arpu统计*/
    @Override
    public ResponseEntity customArpuTime(StatisticsRequest statisticsRequest) {
        List<StatisticsRateResponse> payRespons = payMapper.customArpuTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部arpu统计*/
    @Override
    public ResponseEntity allArpu(StatisticsRequest statisticsRequest) {
        List<StatisticsRateResponse> payRespons = payMapper.allArpu(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期的arppu统计*/
    @Override
    public ResponseEntity customArppuTime(StatisticsRequest statisticsRequest) {
        List<StatisticsRateResponse> payRespons = payMapper.customArppuTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部的arppu统计*/
    @Override
    public ResponseEntity allArppu(StatisticsRequest statisticsRequest) {
        List<StatisticsRateResponse> payRespons = payMapper.allArppu(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期支付玩家的统计*/
    @Override
    public ResponseEntity customPlayersTime(StatisticsRequest statisticsRequest) {
        List<PayResponse> payRespons = payMapper.customPayPlayersTime(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部支付玩家的统计*/
    @Override
        public ResponseEntity allPlayers(StatisticsRequest statisticsRequest) {
        List<PayResponse> payRespons = payMapper.allPayPlayers(statisticsRequest);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }
}
