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
    @Select("SELECT userActive.StatisticsDate AS StatisticsDate,IFNULL((userActive.userActiveCount/userNew.userNewCount)*100,0.0) AS userRate\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount        \n" +
            "    FROM         \n" +
            "    (     \n" +
            "        SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS userActiveCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userLoginTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userLoginTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} and userUseDevice=#{statisticsRequest.userUseDevice}    \n" +
            "        GROUP BY userLoginTime        \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS userActiveCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )    \n" +
            ") AS b      \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS userActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userNewCount,0) AS userNewCount        \n" +
            "    FROM         \n" +
            "    (        \n" +
            "        SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS userNewCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userRegistedTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userRegistedTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} and userUseDevice=#{statisticsRequest.userUseDevice}    \n" +
            "        GROUP BY userRegistedTime    \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT DISTINCT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS userNewCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )         \n" +
            ") AS b        \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS userNew\n" +
            "ON userActive.StatisticsDate=userNew.StatisticsDate\n" +
            "GROUP BY userActive.StatisticsDate")
    List<UserRetainedResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现查询所有用户留存的统计*/
    @Select("SELECT userActive.StatisticsDate AS StatisticsDate,IFNULL((userActive.userActiveCount/userNew.userNewCount)*100,0.0) AS userRate\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount        \n" +
            "    FROM         \n" +
            "    (     \n" +
            "        SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS userActiveCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userLoginTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userLoginTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} and userUseDevice IS NOT NULL \n" +
            "        GROUP BY userLoginTime        \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS userActiveCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )    \n" +
            ") AS b      \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS userActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userNewCount,0) AS userNewCount        \n" +
            "    FROM         \n" +
            "    (        \n" +
            "        SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS userNewCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userRegistedTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userRegistedTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} and userUseDevice IS NOT NULL \n" +
            "        GROUP BY userRegistedTime    \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT DISTINCT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS userNewCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )         \n" +
            ") AS b        \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS userNew\n" +
            "ON userActive.StatisticsDate=userNew.StatisticsDate\n" +
            "GROUP BY userActive.StatisticsDate")
    List<UserRetainedResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
