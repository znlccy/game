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
    @Select("SELECT    \n" +
            "DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userNewCount,0) AS userNewCount    \n" +
            "FROM     \n" +
            "(    \n" +
            "   SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,    \n" +
            "   COUNT(DISTINCT userId) AS userNewCount     \n" +
            "   FROM tb_user_caculator     \n" +
            "   WHERE userRegistedTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userRegistedTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} \n" +
            "   GROUP BY userRegistedTime\n" +
            "UNION    \n" +
            "(    \n" +
            "   SELECT DISTINCT datelist AS StatisticsDate,    \n" +
            "   payRecordTotalAmount AS userNewCount    \n" +
            "   FROM tb_income     \n" +
            "   WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')    \n" +
            ")     \n" +
            ") AS b    \n" +
            "GROUP BY StatisticsDate")
    List<UserNewResponse> cudtomTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    @Select("SELECT    \n" +
            "DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userNewCount,0) AS userNewCount    \n" +
            "FROM     \n" +
            "(    \n" +
            "   SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,    \n" +
            "   COUNT(DISTINCT userId) AS userNewCount     \n" +
            "   FROM tb_user_caculator     \n" +
            "   WHERE userRegistedTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userRegistedTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} \n" +
            "   GROUP BY userRegistedTime\n" +
            "UNION    \n" +
            "(    \n" +
            "   SELECT DISTINCT datelist AS StatisticsDate,    \n" +
            "   payRecordTotalAmount AS userNewCount    \n" +
            "   FROM tb_income     \n" +
            "   WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')    \n" +
            ")     \n" +
            ") AS b    \n" +
            "GROUP BY StatisticsDate")
    List<UserNewResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
