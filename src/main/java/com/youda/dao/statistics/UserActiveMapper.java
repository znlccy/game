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
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回自定义日期的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT userLoginTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "   GROUP BY userLoginTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserActiveResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现所有活跃用户统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回自定义日期的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT userLoginTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone\n" +
            "   GROUP BY userLoginTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserActiveResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
