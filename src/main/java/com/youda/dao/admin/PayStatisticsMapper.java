package com.youda.dao.admin;

import com.youda.response.admin.PayStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:33
 * @Version 1.0.0
 * @Instructions 实现付费统计
 */
@Mapper
public interface PayStatisticsMapper {

    /*实现今天付费率统计*/
    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> todayPayRateStatistics();

    /*实现昨天的付费率统计*/
    @Select("")
    List<PayStatisticsResponse> yestodayPayRateStatistics();

    /*实现一周的付费率统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekPayRateStatistics();

    /*实现一个月付费率统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthPayRateStatistics();

    /*实现自定义日期付费率统计*/
    @Select("")
    List<PayStatisticsResponse> customPayRateStatistics();

    /*实现*/
    @Select("")
    List<PayStatisticsResponse> todayArpuStatistics();

    @Select("")
    List<PayStatisticsResponse> yestodayArpuStatistics();

    @Select("")
    List<PayStatisticsResponse> aWeekArpuStatistics();

    @Select("")
    List<PayStatisticsResponse> aMonthArpuStatistics();

    @Select("")
    List<PayStatisticsResponse> customArpuStatistics();

    @Select("")
    List<PayStatisticsResponse> todayArppuStatistics();

    @Select("")
    List<PayStatisticsResponse> yestodayArppuStatistics();

    @Select("")
    List<PayStatisticsResponse> aWeekArppuStatistics();

    @Select("")
    List<PayStatisticsResponse> aMonthArppuStatistics();

    @Select("")
    List<PayStatisticsResponse> customArppuStatistics();

    @Select("")
    List<PayStatisticsResponse> todayPayingPlayersStatistics();

    @Select("")
    List<PayStatisticsResponse> yestodayPayingPlayersStatistics();

    @Select("")
    List<PayStatisticsResponse> aWeekPayingPlayersStatistics();

    @Select("")
    List<PayStatisticsResponse> aMonthPayingPlayersStatistics();

    @Select("")
    List<PayStatisticsResponse> customPayingPlayersStatistics();

}
