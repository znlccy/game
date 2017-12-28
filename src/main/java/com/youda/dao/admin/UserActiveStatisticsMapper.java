package com.youda.dao.admin;

import com.youda.request.admin.UserStatisticsRequest;
import com.youda.response.admin.UserActiveStatisticsResponse;
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
public interface UserActiveStatisticsMapper {

    /*实现自选日期活跃用户统计*/
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
            "   SELECT userLoginTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{userStatisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{userStatisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{userStatisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{userStatisticsRequest.userUseDevice} \n" +
            "   GROUP BY userLoginTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserActiveStatisticsResponse> customDateActiveUserStatistics(@Param("userStatisticsRequest") UserStatisticsRequest userStatisticsRequest);

    /*实现所有活跃用户统计*/
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
            "   SELECT userLoginTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{userStatisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{userStatisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{userStatisticsRequest.endTime},' 23:59:59') AND userName=a.phone\n" +
            "   GROUP BY userLoginTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserActiveStatisticsResponse> allPlatformActiveUserStatistics(@Param("userStatisticsRequest") UserStatisticsRequest userStatisticsRequest);
}
