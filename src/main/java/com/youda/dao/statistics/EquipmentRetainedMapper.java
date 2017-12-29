package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.EquipmentRetainedResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:23
 * @Version 1.0.0
 * @Instructions 实现设备统计的Dao层
 */

@Mapper
public interface EquipmentRetainedMapper {

    /*实现自定义日期的新增设备的统计*/
    @Select("SELECT equipmentActive.ddate AS ddate,(equipmentActive.equipmentActiveCount/equipmentNew.equipmentNewCount)*100 AS equipmentRate \n" +
            "FROM\n" +
            "(\t\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS equipmentActiveCount\n" +
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
            "\t   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice}\n" +
            "\t   AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != '' \n" +
            "\t   GROUP BY userLoginTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS equipmentActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS equipmentNewCount\n" +
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
            "\t   AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != '' \n" +
            "\t   GROUP BY userRegisteredTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS equipmentNew\n" +
            "ON equipmentActive.ddate = equipmentNew.ddate\n" +
            "GROUP BY equipmentActive.ddate")
    List<EquipmentRetainedResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的新增设备统计*/
    @Select("SELECT equipmentActive.ddate AS ddate,(equipmentActive.equipmentActiveCount/equipmentNew.equipmentNewCount)*100 AS equipmentRate \n" +
            "FROM\n" +
            "(\t\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS equipmentActiveCount\n" +
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
            "\t   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice}\n" +
            "\t   AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != '' \n" +
            "\t   GROUP BY userLoginTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS equipmentActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "\tSELECT\n" +
            "\t    DATE(dday) ddate,\n" +
            "\t    COUNT(*) - 2 AS equipmentNewCount\n" +
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
            "\t   AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != '' \n" +
            "\t   GROUP BY userRegisteredTime\n" +
            "\t   )\n" +
            "\t) AS a\n" +
            "\tGROUP BY ddate\n" +
            ") AS equipmentNew\n" +
            "ON equipmentActive.ddate = equipmentNew.ddate\n" +
            "GROUP BY equipmentActive.ddate")
    List<EquipmentRetainedResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
