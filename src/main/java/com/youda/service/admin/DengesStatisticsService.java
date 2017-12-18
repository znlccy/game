package com.youda.service.admin;

import com.youda.response.admin.PayStatisticsResponse;

public interface DengesStatisticsService {

    /*定义当日付费率的统计功能*/
    PayStatisticsResponse dengesPayRate();

    /*定义当日平均收入*/
    PayStatisticsResponse dengesARPU();

    PayStatisticsResponse dengesARPPU();
}
