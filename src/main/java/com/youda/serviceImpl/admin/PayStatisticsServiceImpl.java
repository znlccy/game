package com.youda.serviceImpl.admin;

import com.youda.dao.admin.PayStatisticsMapper;
import com.youda.response.ResponseStatusCode;
import com.youda.response.admin.PayStatisticsResponse;
import com.youda.service.admin.PayStatisticsService;
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
public class PayStatisticsServiceImpl implements PayStatisticsService {

    /*实现支付统计Dao层的自动依赖注入*/
    @Autowired
    private PayStatisticsMapper payStatisticsMapper;

    /*实现日付费率的统计*/
    @Override
    public ResponseEntity dayPayRateStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.dayPayRateStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现ARPU的统计*/
    @Override
    public ResponseEntity dayArpuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.dayArpuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }

    /*实现ARPPU的统计*/
    @Override
    public ResponseEntity dayArppuStatistics() {
        List<PayStatisticsResponse> payStatisticsResponses = payStatisticsMapper.dayArppuStatistics();
        return ResponseStatusCode.putOrGetSuccess(payStatisticsResponses);
    }
}
