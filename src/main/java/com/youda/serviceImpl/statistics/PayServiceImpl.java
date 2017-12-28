package com.youda.serviceImpl.statistics;

import com.youda.dao.admin.PayStatisticsMapper;
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
    private PayStatisticsMapper payStatisticsMapper;

    /*实现今天的支付率统计*/
    @Override
    public ResponseEntity todayPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.todayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的支付率统计*/
    @Override
    public ResponseEntity yestodayPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.yestodayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的支付率统计*/
    @Override
    public ResponseEntity aWeekPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aWeekPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一月的支付率统计*/
    @Override
    public ResponseEntity aMonthPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aMonthPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期支付率统计*/
    @Override
    public ResponseEntity customPayRateTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.customPayRateStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部支付率统计*/
    @Override
    public ResponseEntity allPayRate() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.allPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现今天的arpu统计*/
    @Override
    public ResponseEntity todayArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.todayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的arpu统计*/
    @Override
    public ResponseEntity yestodayArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.yestodayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的arpu统计*/
    @Override
    public ResponseEntity aWeekArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aWeekArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一月的arpu统计*/
    @Override
    public ResponseEntity aMonthArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aMonthArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期的arpu统计*/
    @Override
    public ResponseEntity customArpuTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.customArpuStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部arpu统计*/
    @Override
    public ResponseEntity allArpu() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.allArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现今天arppu统计*/
    @Override
    public ResponseEntity todayArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.todayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的arppu统计*/
    @Override
    public ResponseEntity yestodayArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.yestodayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的arppu统计*/
    @Override
    public ResponseEntity aWeekArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aWeekArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一个月的arppu统计*/
    @Override
    public ResponseEntity aMonthArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aMonthArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期的arppu统计*/
    @Override
    public ResponseEntity customArppuTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.customArppuStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部的arppu统计*/
    @Override
    public ResponseEntity allArppu() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.allArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现今天的支付玩家统计*/
    @Override
    public ResponseEntity todayPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.todayPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现昨天的支付玩家统计*/
    @Override
    public ResponseEntity yestodayPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.yestodayPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一周的支付玩家的统计*/
    @Override
    public ResponseEntity aWeekPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aWeekPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现一个月的支付玩家的统计*/
    @Override
    public ResponseEntity aMonthPayingPlayersStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.aMonthPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现任意日期支付玩家的统计*/
    @Override
    public ResponseEntity customPlayersTime(String beginTime, String endTime) {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.customPayingPlayersStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现全部支付玩家的统计*/
    @Override
    public ResponseEntity allPlayers() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.allPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }
}
