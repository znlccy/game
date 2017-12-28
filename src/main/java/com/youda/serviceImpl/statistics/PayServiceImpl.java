package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.PayMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.PayStatisticsResponse;
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

    /*实现今天的支付率统计*/
    @Override
    public ResponseEntity todayPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.todayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的支付率统计*/
    @Override
    public ResponseEntity yestodayPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.yestodayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的支付率统计*/
    @Override
    public ResponseEntity aWeekPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aWeekPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一月的支付率统计*/
    @Override
    public ResponseEntity aMonthPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aMonthPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期支付率统计*/
    @Override
    public ResponseEntity customPayRateTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.customPayRateStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部支付率统计*/
    @Override
    public ResponseEntity allPayRate() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.allPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现今天的arpu统计*/
    @Override
    public ResponseEntity todayArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.todayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的arpu统计*/
    @Override
    public ResponseEntity yestodayArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.yestodayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的arpu统计*/
    @Override
    public ResponseEntity aWeekArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aWeekArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一月的arpu统计*/
    @Override
    public ResponseEntity aMonthArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aMonthArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期的arpu统计*/
    @Override
    public ResponseEntity customArpuTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.customArpuStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部arpu统计*/
    @Override
    public ResponseEntity allArpu() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.allArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现今天arppu统计*/
    @Override
    public ResponseEntity todayArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.todayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的arppu统计*/
    @Override
    public ResponseEntity yestodayArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.yestodayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的arppu统计*/
    @Override
    public ResponseEntity aWeekArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aWeekArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一个月的arppu统计*/
    @Override
    public ResponseEntity aMonthArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aMonthArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期的arppu统计*/
    @Override
    public ResponseEntity customArppuTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.customArppuStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部的arppu统计*/
    @Override
    public ResponseEntity allArppu() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.allArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现今天的支付玩家统计*/
    @Override
    public ResponseEntity todayPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.todayPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的支付玩家统计*/
    @Override
    public ResponseEntity yestodayPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.yestodayPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的支付玩家的统计*/
    @Override
    public ResponseEntity aWeekPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aWeekPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一个月的支付玩家的统计*/
    @Override
    public ResponseEntity aMonthPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.aMonthPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期支付玩家的统计*/
    @Override
    public ResponseEntity customPlayersTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.customPayingPlayersStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部支付玩家的统计*/
    @Override
    public ResponseEntity allPlayers() {
        List<PayStatisticsResponse> payStatisticsResponses = payMapper.allPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }
}
