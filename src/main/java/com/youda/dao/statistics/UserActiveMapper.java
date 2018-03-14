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
    @Select("SELECT     \n" +
            "DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userActiveCount),0) AS userActiveCount     \n" +
            "FROM      \n" +
            "(  \n" +
            "   SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userActiveCount) AS userActiveCount FROM \n" +
            "   (SELECT DATE(userRegistedTime) AS StatisticsDate,     \n" +
            "   COUNT(DISTINCT userId) AS userActiveCount      \n" +
            "   FROM tb_user_caculator      \n" +
            "   WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice=#{statisticsRequest.userUseDevice}\n" +
            "   GROUP BY userLoginTime) AS a\n" +
            "   GROUP BY a.StatisticsDate\n" +
            "UNION     \n" +
            "(     \n" +
            "   SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "   payRecordTotalAmount AS userActiveCount     \n" +
            "   FROM tb_income      \n" +
            "   WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')  \n" +
            ")      \n" +
            ") AS b     \n" +
            "GROUP BY StatisticsDate")
    List<UserActiveResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现所有活跃用户统计*/
    @Select("SELECT     \n" +
            "DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userActiveCount),0) AS userActiveCount     \n" +
            "FROM      \n" +
            "(  \n" +
            "   SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userActiveCount) AS userActiveCount FROM \n" +
            "   (SELECT DATE(userRegistedTime) AS StatisticsDate,     \n" +
            "   COUNT(DISTINCT userId) AS userActiveCount      \n" +
            "   FROM tb_user_caculator      \n" +
            "   WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL\n" +
            "   GROUP BY userLoginTime) AS a\n" +
            "   GROUP BY a.StatisticsDate\n" +
            "UNION     \n" +
            "(     \n" +
            "   SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "   payRecordTotalAmount AS userActiveCount     \n" +
            "   FROM tb_income      \n" +
            "   WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')  \n" +
            ")      \n" +
            ") AS b     \n" +
            "GROUP BY StatisticsDate")
    List<UserActiveResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    @Select("SELECT\n" +
            "    period,amount\n" +
            "FROM\n" +
            "    (\n" +
            "\tSELECT\n" +
            "            HOUR(userLoginTime) AS period, COUNT(*) AS amount \n" +
            "            FROM\n" +
            "                tb_user_caculator \n" +
            "            WHERE gameChannelId = #{periodRequest.gameChannelId} AND \n" +
            "            userLoginTime BETWEEN DATE_FORMAT(CONCAT(#{periodRequest.statisticsDate},' 00:00:00'),'%Y-%m-%d %H:%i:%s') \n" +
            "            AND DATE_FORMAT(CONCAT(#{periodRequest.statisticsDate},' 23:59:59'),'%Y-%m-%d %H:%i:%s')\n" +
            "            GROUP BY period\n" +
            "        UNION ALL\n" +
            "        SELECT\n" +
            "            period, amount\n" +
            "        FROM\n" +
            "            tb_time \n" +
            "    ) a\n" +
            "GROUP BY period")
    List<PeriodResponse> periodStatistics(@Param("periodRequest") PeriodRequest periodRequest);
}
