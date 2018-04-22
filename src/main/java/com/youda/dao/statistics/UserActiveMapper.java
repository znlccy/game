package com.youda.dao.statistics;

import com.youda.request.statistics.PeriodRequest;
import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.PeriodResponse;
import com.youda.response.statistics.UserActiveResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:30
 * @Version 1.0.0
 * @Instructions 定义用户活跃数统计的Dao层
 */

@Mapper
public interface UserActiveMapper {

    /*实现自选日期活跃用户统计*/
    @Select("SELECT DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userActiveCount),0) AS userActiveCount FROM \n" +
            "(SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userActiveCount) AS userActiveCount FROM \n" +
            "(SELECT DATE(userRegistedTime) AS StatisticsDate,COUNT(DISTINCT userId) AS userActiveCount FROM tb_user_caculator \n" +
            "WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL \n" +
            "GROUP BY userLoginTime) AS a WHERE a.StatisticsDate>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && a.StatisticsDate<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') GROUP BY a.StatisticsDate \n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS userActiveCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate ")
    List<UserActiveResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现所有活跃用户统计*/
    @Select("SELECT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userActiveCount),0) AS userActiveCount FROM \n" +
            "(SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userActiveCount) AS userActiveCount FROM \n" +
            "(SELECT DATE(userRegistedTime) AS StatisticsDate,COUNT(DISTINCT userId) AS userActiveCount FROM tb_user_caculator \n" +
            "WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL \n" +
            "GROUP BY userLoginTime) AS a WHERE a.StatisticsDate>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && a.StatisticsDate<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') GROUP BY a.StatisticsDate \n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS userActiveCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate")
    List<UserActiveResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    @Select("SELECT period,amount FROM(SELECT HOUR(userRegistedTime) AS period, COUNT(DISTINCT userId) AS amount FROM tb_user_caculator \n" +
            "WHERE gameChannelId = #{periodRequest.gameChannelId} AND userRegistedTime BETWEEN DATE_FORMAT(CONCAT(#{periodRequest.statisticsDate},' 00:00:00'),'%Y-%m-%d %H:%i:%s') AND DATE_FORMAT(CONCAT(#{periodRequest.statisticsDate},' 23:59:59'),'%Y-%m-%d %H:%i:%s') GROUP BY period \n" +
            "UNION ALL \n" +
            "SELECT period, amount FROM tb_time ) a GROUP BY period ")
    List<PeriodResponse> periodStatistics(@Param("periodRequest") PeriodRequest periodRequest);
}
