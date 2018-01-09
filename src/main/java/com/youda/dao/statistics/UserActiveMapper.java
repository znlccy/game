package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
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
    @Select("SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount    \n" +
            "FROM     \n" +
            "( \n" +
            "   SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,    \n" +
            "   COUNT(DISTINCT userId) AS userActiveCount     \n" +
            "   FROM tb_user_caculator     \n" +
            "   WHERE userLoginTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userLoginTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId}\n" +
            "   GROUP BY userLoginTime    \n" +
            "UNION    \n" +
            "(    \n" +
            "   SELECT datelist AS StatisticsDate,    \n" +
            "   payRecordTotalAmount AS userActiveCount    \n" +
            "   FROM tb_income     \n" +
            "   WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')    \n" +
            ")\n" +
            ") AS b  \n" +
            "GROUP BY StatisticsDate")
    List<UserActiveResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现所有活跃用户统计*/
    @Select("SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount    \n" +
            "FROM     \n" +
            "( \n" +
            "   SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,    \n" +
            "   COUNT(DISTINCT userId) AS userActiveCount     \n" +
            "   FROM tb_user_caculator     \n" +
            "   WHERE userLoginTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userLoginTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId}  \n" +
            "   GROUP BY userLoginTime    \n" +
            "UNION    \n" +
            "(    \n" +
            "   SELECT datelist AS StatisticsDate,    \n" +
            "   payRecordTotalAmount AS userActiveCount    \n" +
            "   FROM tb_income     \n" +
            "   WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')    \n" +
            ")\n" +
            ") AS b  \n" +
            "GROUP BY StatisticsDate")
    List<UserActiveResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
