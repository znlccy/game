package com.youda.dao.statistics;

import com.youda.request.admin.UserStatisticsRequest;
import com.youda.response.admin.UserNewStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserNewMapper {

    /*实现自定义日期查询*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回自定义日期的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{userStatisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{userStatisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT userRegisteredTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{userStatisticsRequest.gameName}))) AS a \n" +
            "   WHERE userRegisteredTime >=CONCAT(#{userStatisticsRequest.beginTime},' 00:00:00') && userRegisteredTime<=CONCAT(#{userStatisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{userStatisticsRequest.userUseDevice} \n" +
            "   GROUP BY userRegisteredTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserNewStatisticsResponse> cudtomTime(@Param("userStatisticsRequest") UserStatisticsRequest userStatisticsRequest);

    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回自定义日期的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{userStatisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{userStatisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT userRegisteredTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{userStatisticsRequest.gameName}))) AS a \n" +
            "   WHERE userRegisteredTime >=CONCAT(#{userStatisticsRequest.beginTime},' 00:00:00') && userRegisteredTime<=CONCAT(#{userStatisticsRequest.endTime},' 23:59:59') AND userName=a.phone\n" +
            "   GROUP BY userRegisteredTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserNewStatisticsResponse> all(@Param("userStatisticsRequest") UserStatisticsRequest userStatisticsRequest);
}
