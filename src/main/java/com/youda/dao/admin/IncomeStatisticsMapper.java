package com.youda.dao.admin;

import com.youda.response.admin.IncomeStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/20 14:49
 * @Version 1.0.0
 * @Instructions 定义收入统计接口
 */

@Mapper
public interface IncomeStatisticsMapper {

    /*定义今天的收入统计*/
    @Select("")
    List<IncomeStatisticsResponse> todayIncomeStatistics();

    /*定义昨天的收入统计*/
    @Select("")
    List<IncomeStatisticsResponse> yestodayIncomeStatistics();

    /*定义一周的收入统计*/
    @Select("")
    List<IncomeStatisticsResponse> aWeekIncomeStatistics();

    /*定义一个月的收入统计*/
    @Select("")
    List<IncomeStatisticsResponse> aMonthIncomeStatistics();

    /*定义任意日期的收入统计*/
    @Select("")
    List<IncomeStatisticsResponse> customIncomeStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /*定义全部的收入统计*/
    @Select("")
    List<IncomeStatisticsResponse> allIncomeStatistics();
}
