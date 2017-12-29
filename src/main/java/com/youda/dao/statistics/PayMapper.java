package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.PayResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:33
 * @Version 1.0.0
 * @Instructions 实现付费统计
 */
@Mapper
public interface PayMapper {

    /*实现自定义日期付费率统计*/
    @Select("SELECT (payPlayerCount.payCount/activeUserCount.userActiveCount)*100 AS payCount,payPlayerCount.ddate AS ddate FROM \n" +
            "(\n" +
            "    SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS payCount\n" +
            "FROM \n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT payRecordTime FROM tb_payrecord AS d,\n" +
            "   (\n" +
            "   SELECT otherOrderId FROM tb_order AS b,\n" +
            "   ( \n" +
            "   SELECT userLoginTime,userId FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "   GROUP BY userLoginTime\n" +
            "   ) AS c\n" +
            "   WHERE b.userId=c.userId\n" +
            "   ) AS e\n" +
            "   WHERE d.outTradeNo=e.otherOrderId AND payRecordStatus='1'\n" +
            "   GROUP BY d.payRecordTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate\n" +
            ") AS payPlayerCount\n" +
            "INNER JOIN\n" +
            "(\n" +
            "    SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
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
            "GROUP BY ddate\n" +
            ") AS activeUserCount\n" +
            "ON payPlayerCount.ddate=activeUserCount.ddate")
    List<PayResponse> customPayRateTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的付费率统计*/
    @Select("SELECT (payPlayerCount.payCount/activeUserCount.userActiveCount)*100 AS payCount, payPlayerCount.ddate AS ddate FROM \n" +
            "(\n" +
            "    SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS payCount\n" +
            "FROM \n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT payRecordTime FROM tb_payrecord AS d,\n" +
            "   (\n" +
            "   SELECT otherOrderId FROM tb_order AS b,\n" +
            "   ( \n" +
            "   SELECT userLoginTime,userId FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone \n" +
            "   GROUP BY userLoginTime\n" +
            "   ) AS c\n" +
            "   WHERE b.userId=c.userId\n" +
            "   ) AS e\n" +
            "   WHERE d.outTradeNo=e.otherOrderId AND payRecordStatus='1'\n" +
            "   GROUP BY d.payRecordTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate\n" +
            ") AS payPlayerCount\n" +
            "INNER JOIN\n" +
            "(\n" +
            "    SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT userLoginTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone \n" +
            "   GROUP BY userLoginTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate\n" +
            ") AS activeUserCount\n" +
            "ON payPlayerCount.ddate=activeUserCount.ddate")
    List<PayResponse> allPayRate(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现自定义的ARPU的统计*/
    @Select("SELECT customActiveUser.ddate AS ddate,(customPayCount.payCount/customActiveUser.activeUserCount) AS payCount      \n" +
            "FROM       \n" +
            "(SELECT      \n" +
            "DATE(dday) ddate,      \n" +
            "COUNT(*) - 2 AS activeUserCount      \n" +
            "FROM      \n" +
            "(      \n" +
            "   SELECT datelist AS dday      \n" +
            "   FROM      \n" +
            "   tb_calendar       \n" +
            "   -- 这里是限制返回最近30天的数据          \n" +
            "   WHERE  CONCAT(#{beginTime},' 00:00:00')<= DATE(datelist)&&DATE(datelist)<=CONCAT(#{endTime},' 24:00:00')      \n" +
            "   UNION ALL      \n" +
            "   SELECT userLoginTime      \n" +
            "   FROM      \n" +
            "   tb_user      \n" +
            "   WHERE  userLoginTime>=CONCAT(#{beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{endTime},' 24:00:00')      \n" +
            "   GROUP BY userLoginTime      \n" +
            "   ) a      \n" +
            "   GROUP BY ddate ) AS customActiveUser                   \n" +
            "   INNER JOIN                   \n" +
            "   (SELECT DATE(dday) ddate,SUM(payRecordTotalAmount) AS payCount       \n" +
            "       FROM       \n" +
            "       (       \n" +
            "           SELECT datelist AS dday,payRecordTotalAmount       \n" +
            "           FROM       \n" +
            "           tb_income        \n" +
            "           -- 这里是限制返回最近一周的数据             \n" +
            "           WHERE CONCAT(#{beginTime},' 00:00:00')<= DATE(datelist)&&DATE(datelist)<=CONCAT(#{endTime},' 24:00:00')      \n" +
            "           UNION ALL       \n" +
            "           SELECT payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount       \n" +
            "           FROM        \n" +
            "           tb_payrecord       \n" +
            "           WHERE payRecordTime >=CONCAT(#{beginTime},' 00:00:00') AND payRecordTime<=CONCAT(#{endTime},' 24:00:00')       \n" +
            "           GROUP BY payRecordTime       \n" +
            "       ) a       \n" +
            "       GROUP BY ddate) AS customPayCount      \n" +
            "ON customActiveUser.ddate = customPayCount.ddate")
    List<PayResponse> customArpuTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的ARPU统计*/
    @Select("SELECT (allPayCount.payCount/allActiveUser.activeUserCount) AS payCount,allActiveUser.ddate AS ddate \n" +
            "FROM       \n" +
            "(SELECT      \n" +
            "DATE(dday) ddate,      \n" +
            "COUNT(*) - 2 AS activeUserCount      \n" +
            "FROM      \n" +
            "(      \n" +
            "   SELECT datelist AS dday      \n" +
            "   FROM      \n" +
            "   tb_calendar       \n" +
            "   -- 这里是限制返回最近30天的数据          \n" +
            "   WHERE DATE(datelist)<=CONCAT(CURDATE(),' 24:00:00')      \n" +
            "   UNION ALL      \n" +
            "   SELECT userLoginTime      \n" +
            "   FROM      \n" +
            "   tb_user      \n" +
            "   WHERE userLoginTime<=CONCAT(CURDATE(),' 24:00:00')    \n" +
            "   GROUP BY userLoginTime      \n" +
            "   ) a      \n" +
            "   GROUP BY ddate ) AS allActiveUser                   \n" +
            "   INNER JOIN                   \n" +
            "   (SELECT DATE(dday) ddate,SUM(payRecordTotalAmount) AS payCount       \n" +
            "       FROM       \n" +
            "       (       \n" +
            "           SELECT datelist AS dday,payRecordTotalAmount       \n" +
            "           FROM       \n" +
            "           tb_income        \n" +
            "           -- 这里是限制返回最近一周的数据             \n" +
            "           WHERE DATE(datelist)<=CONCAT(CURDATE(),' 24:00:00')  \n" +
            "           UNION ALL       \n" +
            "           SELECT payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount       \n" +
            "           FROM        \n" +
            "           tb_payrecord       \n" +
            "           WHERE payRecordTime<=CONCAT(CURDATE(),' 24:00:00')        \n" +
            "           GROUP BY payRecordTime       \n" +
            "       ) a       \n" +
            "       GROUP BY ddate) AS allPayCount      \n" +
            "ON allActiveUser.ddate = allPayCount.ddate")
    List<PayResponse> allArpu(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现自定义日期的ARPPU统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    SUM(payRecordTotalAmount)/(COUNT(*) - 2) AS payCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday,payRecordTotalAmount\n" +
            "        FROM\n" +
            "            tb_income \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  CONCAT(#{beginTime},' 00:00:00')<= DATE(datelist)&&DATE(datelist)<=CONCAT(#{endTime},' 24:00:00')\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount\n" +
            "            FROM\n" +
            "                tb_payrecord\n" +
            "            WHERE  payRecordTime>=CONCAT(#{beginTime},' 00:00:00') && payRecordTime<=CONCAT(#{endTime},' 24:00:00')\n" +
            "            GROUP BY payRecordTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<PayResponse> customArppuTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*s辉县全部ARPPU统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    SUM(payRecordTotalAmount)/(COUNT(*) - 2) AS payCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday,payRecordTotalAmount\n" +
            "        FROM\n" +
            "            tb_income \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE DATE(datelist)<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount\n" +
            "            FROM\n" +
            "                tb_payrecord\n" +
            "            WHERE  payRecordTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "            GROUP BY payRecordTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<PayResponse> allArppu(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现任意日期支付玩家统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS payCount\n" +
            "FROM \n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT payRecordTime FROM tb_payrecord AS d,\n" +
            "   (\n" +
            "   SELECT otherOrderId FROM tb_order AS b,\n" +
            "   ( \n" +
            "   SELECT userLoginTime,userId FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "   GROUP BY userLoginTime\n" +
            "   ) AS c\n" +
            "   WHERE b.userId=c.userId\n" +
            "   ) AS e\n" +
            "   WHERE d.outTradeNo=e.otherOrderId AND payRecordStatus='1'\n" +
            "   GROUP BY d.payRecordTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<PayResponse> customPayPlayersTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的付费玩家统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS payCount\n" +
            "FROM \n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT payRecordTime FROM tb_payrecord AS d,\n" +
            "   (\n" +
            "   SELECT otherOrderId FROM tb_order AS b,\n" +
            "   ( \n" +
            "   SELECT userLoginTime,userId FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone \n" +
            "   GROUP BY userLoginTime\n" +
            "   ) AS c\n" +
            "   WHERE b.userId=c.userId\n" +
            "   ) AS e\n" +
            "   WHERE d.outTradeNo=e.otherOrderId AND payRecordStatus='1'\n" +
            "   GROUP BY d.payRecordTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<PayResponse> allPayPlayers(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
