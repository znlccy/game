package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.PayMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.PayResponse;
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
        List<PayResponse> payRespons = payMapper.todayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现昨天的支付率统计*/
    @Override
    public ResponseEntity yestodayPayRateStatistics() {
        List<PayResponse> payRespons = payMapper.yestodayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一周的支付率统计*/
    @Override
    public ResponseEntity aWeekPayRateStatistics() {
        List<PayResponse> payRespons = payMapper.aWeekPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一月的支付率统计*/
    @Override
    public ResponseEntity aMonthPayRateStatistics() {
        List<PayResponse> payRespons = payMapper.aMonthPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期支付率统计*/
    @Override
    public ResponseEntity customPayRateTime(String beginTime, String endTime) {
        List<PayResponse> payRespons = payMapper.customPayRateStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部支付率统计*/
    @Override
    public ResponseEntity allPayRate() {
        List<PayResponse> payRespons = payMapper.allPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现今天的arpu统计*/
    @Override
    public ResponseEntity todayArpuStatistics() {
        List<PayResponse> payRespons = payMapper.todayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现昨天的arpu统计*/
    @Override
    public ResponseEntity yestodayArpuStatistics() {
        List<PayResponse> payRespons = payMapper.yestodayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一周的arpu统计*/
    @Override
    public ResponseEntity aWeekArpuStatistics() {
        List<PayResponse> payRespons = payMapper.aWeekArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一月的arpu统计*/
    @Override
    public ResponseEntity aMonthArpuStatistics() {
        List<PayResponse> payRespons = payMapper.aMonthArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期的arpu统计*/
    @Override
    public ResponseEntity customArpuTime(String beginTime, String endTime) {
        List<PayResponse> payRespons = payMapper.customArpuStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部arpu统计*/
    @Override
    public ResponseEntity allArpu() {
        List<PayResponse> payRespons = payMapper.allArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现今天arppu统计*/
    @Override
    public ResponseEntity todayArppuStatistics() {
        List<PayResponse> payRespons = payMapper.todayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现昨天的arppu统计*/
    @Override
    public ResponseEntity yestodayArppuStatistics() {
        List<PayResponse> payRespons = payMapper.yestodayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一周的arppu统计*/
    @Override
    public ResponseEntity aWeekArppuStatistics() {
        List<PayResponse> payRespons = payMapper.aWeekArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一个月的arppu统计*/
    @Override
    public ResponseEntity aMonthArppuStatistics() {
        List<PayResponse> payRespons = payMapper.aMonthArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期的arppu统计*/
    @Override
    public ResponseEntity customArppuTime(String beginTime, String endTime) {
        List<PayResponse> payRespons = payMapper.customArppuStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部的arppu统计*/
    @Override
    public ResponseEntity allArppu() {
        List<PayResponse> payRespons = payMapper.allArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现今天的支付玩家统计*/
    @Override
    public ResponseEntity todayPayingPlayersStatistics() {
        List<PayResponse> payRespons = payMapper.todayPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现昨天的支付玩家统计*/
    @Override
    public ResponseEntity yestodayPayingPlayersStatistics() {
        List<PayResponse> payRespons = payMapper.yestodayPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一周的支付玩家的统计*/
    @Override
    public ResponseEntity aWeekPayingPlayersStatistics() {
        List<PayResponse> payRespons = payMapper.aWeekPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现一个月的支付玩家的统计*/
    @Override
    public ResponseEntity aMonthPayingPlayersStatistics() {
        List<PayResponse> payRespons = payMapper.aMonthPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现任意日期支付玩家的统计*/
    @Override
    public ResponseEntity customPlayersTime(String beginTime, String endTime) {
        List<PayResponse> payRespons = payMapper.customPayingPlayersStatistics(beginTime, endTime);
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }

    /*实现全部支付玩家的统计*/
    @Override
    public ResponseEntity allPlayers() {
        List<PayResponse> payRespons = payMapper.allPayingPlayersStatistics();
        return ResponseStatusCode.putOrGetSuccess(payRespons);
    }
}
