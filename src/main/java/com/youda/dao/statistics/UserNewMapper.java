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
    @Select("SELECT DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userNewCount),0) AS userNewCount FROM \n" +
            "(SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userNewCount) AS userNewCount FROM \n" +
            "(SELECT DATE(userRegistedTime) AS StatisticsDate,COUNT(DISTINCT userId) AS userNewCount FROM tb_user_caculator \n" +
            "WHERE userRegistedTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userRegistedTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice=#{statisticsRequest.userUseDevice} GROUP BY userRegistedTime) AS a GROUP BY a.StatisticsDate \n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS userNewCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate")
    List<UserNewResponse> cudtomTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    @Select("SELECT DISTINCT DISTINCT StatisticsDate AS StatisticsDate,IFNULL(SUM(userNewCount),0) AS userNewCount FROM \n" +
            "(SELECT a.StatisticsDate AS StatisticsDate, SUM(a.userNewCount) AS userNewCount FROM \n" +
            "(SELECT DATE(userRegistedTime) AS StatisticsDate,COUNT(DISTINCT userId) AS userNewCount FROM tb_user_caculator \n" +
            "WHERE userRegistedTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userRegistedTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL GROUP BY userRegistedTime) AS a GROUP BY a.StatisticsDate \n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS userNewCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') <= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate")
    List<UserNewResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
