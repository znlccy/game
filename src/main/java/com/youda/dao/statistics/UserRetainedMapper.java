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
    @Select("SELECT userActive.ddate AS ddate,(userActive.userActiveCount/userNew.newUserCount)*100 AS userRetainedCount \n" +
            "FROM\n" +
            "(\t\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS userActiveCount\n" +
            "\tFROM\n" +
            "\t(\n" +
            "\t(\n" +
            "\t   SELECT datelist AS dday\n" +
            "\t   FROM tb_calendar \n" +
            "\t   -- 这里是限制返回最近30天的数据\n" +
            "\t   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "\t   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            "\t)\n" +
            "\tUNION ALL\n" +
            "\t(\n" +
            "\t   SELECT userLoginTime FROM tb_user ,(\n" +
            "\t   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "\t   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "\t   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "\t   GROUP BY userLoginTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS userActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS newUserCount\n" +
            "\tFROM\n" +
            "\t(\n" +
            "\t(\n" +
            "\t   SELECT datelist AS dday\n" +
            "\t   FROM tb_calendar \n" +
            "\t   -- 这里是限制返回最近30天的数据\n" +
            "\t   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "\t   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            "\t)\n" +
            "\tUNION ALL\n" +
            "\t(\n" +
            "\t   SELECT userRegisteredTime FROM tb_user ,(\n" +
            "\t   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "\t   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "\t   WHERE userRegisteredTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userRegisteredTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "\t   GROUP BY userRegisteredTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS userNew\n" +
            "ON userActive.ddate = userNew.ddate\n" +
            "GROUP BY userActive.ddate")
    List<UserRetainedResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现查询所有用户留存的统计*/
    @Select("SELECT userActive.ddate AS ddate,(userActive.userActiveCount/userNew.newUserCount)*100 AS userRetainedCount \n" +
            "FROM\n" +
            "(\t\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS userActiveCount\n" +
            "\tFROM\n" +
            "\t(\n" +
            "\t(\n" +
            "\t   SELECT datelist AS dday\n" +
            "\t   FROM tb_calendar \n" +
            "\t   -- 这里是限制返回最近30天的数据\n" +
            "\t   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "\t   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            "\t)\n" +
            "\tUNION ALL\n" +
            "\t(\n" +
            "\t   SELECT userLoginTime FROM tb_user ,(\n" +
            "\t   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "\t   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "\t   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone\n" +
            "\t   GROUP BY userLoginTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS userActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS newUserCount\n" +
            "\tFROM\n" +
            "\t(\n" +
            "\t(\n" +
            "\t   SELECT datelist AS dday\n" +
            "\t   FROM tb_calendar \n" +
            "\t   -- 这里是限制返回最近30天的数据\n" +
            "\t   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "\t   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            "\t)\n" +
            "\tUNION ALL\n" +
            "\t(\n" +
            "\t   SELECT userRegisteredTime FROM tb_user ,(\n" +
            "\t   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "\t   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "\t   WHERE userRegisteredTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userRegisteredTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone \n" +
            "\t   GROUP BY userRegisteredTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS userNew\n" +
            "ON userActive.ddate = userNew.ddate\n" +
            "GROUP BY userActive.ddate")
    List<UserRetainedResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
