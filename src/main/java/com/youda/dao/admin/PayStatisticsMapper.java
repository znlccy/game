package com.youda.dao.admin;

import com.youda.response.admin.PayStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Select("")
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
    List<PayStatisticsResponse> customPayRateStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今天的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> todayArpuStatistics();

    /*实现昨天的ARPPU的统计*/
    @Select("")
    List<PayStatisticsResponse> yestodayArpuStatistics();

    /*实现一周的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekArpuStatistics();

    /*实现一个月的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthArpuStatistics();

    /*实现自定义的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> customArpuStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今天的ARPPU的统计*/
    @Select("")
    List<PayStatisticsResponse> todayArppuStatistics();

    /*实现昨天的ARPPU的统计*/
    @Select("")
    List<PayStatisticsResponse> yestodayArppuStatistics();

    /*实现一周的ARPPU统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekArppuStatistics();

    /*实现一个月的ARPPU统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthArppuStatistics();

    /*实现自定义日期的ARPPU统计*/
    @Select("")
    List<PayStatisticsResponse> customArppuStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今天的支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> todayPayingPlayersStatistics();

    /*实现昨天的支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> yestodayPayingPlayersStatistics();

    /*实现一周支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekPayingPlayersStatistics();

    /*实现一个月支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthPayingPlayersStatistics();

    /*实现任意日期支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> customPayingPlayersStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

}
