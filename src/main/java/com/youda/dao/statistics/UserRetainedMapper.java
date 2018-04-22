package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.UserRetainedResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:36
 * @Version 1.0.0
 * @Instructions 实现用户留存Dao层接口
 */

@Mapper
public interface UserRetainedMapper {

    /*实现自定义日期用户留存统计*/
    @Select("SELECT userActive.StatisticsDate AS StatisticsDate,IFNULL((userActive.userActiveCount/userNew.userNewCount)*100,0.0000) AS userRate FROM    \n" +
            "(SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount FROM             \n" +
            "(SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,COUNT(DISTINCT userId) AS userActiveCount FROM tb_user_caculator             \n" +
            "WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice=#{statisticsRequest.userUseDevice} GROUP BY userLoginTime \n" +
            "UNION \n" +
            "(SELECT datelist AS StatisticsDate,payRecordTotalAmount AS userActiveCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate) AS userActive \n" +
            "RIGHT JOIN \n" +
            "(SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userNewCount,0) AS userNewCount FROM \n" +
            "(SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,COUNT(DISTINCT userId) AS userNewCount FROM tb_user_caculator \n" +
            "WHERE userRegistedTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userRegistedTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice=#{statisticsRequest.userUseDevice} GROUP BY userRegistedTime \n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS userNewCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate) AS userNew ON userActive.StatisticsDate=userNew.StatisticsDate GROUP BY userActive.StatisticsDate")
    List<UserRetainedResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现查询所有用户留存的统计*/
    @Select("SELECT userActive.StatisticsDate AS StatisticsDate,CONVERT(IFNULL((userActive.userActiveCount/(userNew.userNewCount 800))*100,0.0000), DECIMAL(12,4)) AS userRate, userActive.userActiveCount,userNew.userNewCount FROM \n" +
            "(SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount FROM \n" +
            "(SELECT DATE(b.userLoginTime) AS StatisticsDate,SUM(b.userActiveCount) AS userActiveCount FROM (SELECT COUNT(DISTINCT(userId)) AS userActiveCount,userLoginTime,gameChannelId,userUseDevice FROM tb_user_caculator GROUP BY userLoginTime) AS b \n" +
            "WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime <=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND b.gameChannelId=#{statisticsRequest.gameChannelId} AND b.userUseDevice IS NOT NULL GROUP BY DATE_FORMAT(b.userLoginTime, '%Y-%m-%d') \n" +
            "UNION \n" +
            "(SELECT DISTINCT(datelist) AS StatisticsDate,payRecordTotalAmount AS userActiveCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate) AS userActive \n" +
            "RIGHT JOIN \n" +
            "(SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userNewCount,0) AS userNewCount FROM \n" +
            "(SELECT DATE(b.userRegistedTime) AS StatisticsDate,SUM(b.userNewCount) AS userNewCount FROM  (SELECT COUNT(DISTINCT(userId)) AS userNewCount,userRegistedTime,gameChannelId,userUseDevice FROM tb_user_caculator GROUP BY userRegistedTime) AS b \n" +
            "WHERE userRegistedTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userRegistedTime <=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND b.gameChannelId=#{statisticsRequest.gameChannelId} AND b.userUseDevice IS NOT NULL \n" +
            "GROUP BY DATE_FORMAT(b.userRegistedTime, '%Y-%m-%d')\n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS userNewCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate) AS userNew ON userActive.StatisticsDate=userNew.StatisticsDate GROUP BY userActive.StatisticsDate")
    List<UserRetainedResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
