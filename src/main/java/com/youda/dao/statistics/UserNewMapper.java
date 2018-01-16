package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.UserNewResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserNewMapper {

    /*实现自定义日期查询*/
    @Select("SELECT     \n" +
            "DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userNewCount),0) AS userNewCount     \n" +
            "FROM      \n" +
            "(  \n" +
            "   SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userNewCount) AS userNewCount FROM \n" +
            "   (SELECT DATE(userRegistedTime) AS StatisticsDate,     \n" +
            "   COUNT(DISTINCT userId) AS userNewCount      \n" +
            "   FROM tb_user_caculator      \n" +
            "   WHERE userRegistedTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userRegistedTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice=#{statisticsRequest.userUseDevice}\n" +
            "   GROUP BY userRegistedTime) AS a\n" +
            "   GROUP BY a.StatisticsDate\n" +
            "UNION     \n" +
            "(     \n" +
            "   SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "   payRecordTotalAmount AS userNewCount     \n" +
            "   FROM tb_income      \n" +
            "   WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')  \n" +
            ")      \n" +
            ") AS b     \n" +
            "GROUP BY StatisticsDate")
    List<UserNewResponse> cudtomTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    @Select("SELECT     \n" +
            "DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userNewCount),0) AS userNewCount     \n" +
            "FROM      \n" +
            "(  \n" +
            "   SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userNewCount) AS userNewCount FROM \n" +
            "   (SELECT DATE(userRegistedTime) AS StatisticsDate,     \n" +
            "   COUNT(DISTINCT userId) AS userNewCount      \n" +
            "   FROM tb_user_caculator      \n" +
            "   WHERE userRegistedTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userRegistedTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL\n" +
            "   GROUP BY userRegistedTime) AS a\n" +
            "   GROUP BY a.StatisticsDate\n" +
            "UNION     \n" +
            "(     \n" +
            "   SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "   payRecordTotalAmount AS userNewCount     \n" +
            "   FROM tb_income      \n" +
            "   WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')  \n" +
            ")      \n" +
            ") AS b     \n" +
            "GROUP BY StatisticsDate")
    List<UserNewResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
