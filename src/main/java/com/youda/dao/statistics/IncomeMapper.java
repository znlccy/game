package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.IncomeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/20 14:49
 * @Version 1.0.0
 * @Instructions 定义收入统计接口
 */

@Mapper
public interface IncomeMapper {

    /*定义任意日期的收入统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS incomeCount,\n" +
            "    payRecordTotalAmount AS inComeTotalMoney\n" +
            "FROM \n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday,payRecordTotalAmount\n" +
            "   FROM tb_income \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount FROM tb_payrecord AS d,\n" +
            "   (\n" +
            "   SELECT otherOrderId FROM tb_order AS b,\n" +
            "   ( \n" +
            "   SELECT userLoginTime,userId FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{statisticsRequest.userUseDevice}\n" +
            "   GROUP BY userLoginTime\n" +
            "   ) AS c\n" +
            "   WHERE b.userId=c.userId\n" +
            "   ) AS e\n" +
            "   WHERE d.outTradeNo=e.otherOrderId AND payRecordStatus='1'\n" +
            "   GROUP BY d.payRecordTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<IncomeResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*定义全部的收入统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS incomeCount,\n" +
            "    payRecordTotalAmount AS inComeTotalMoney\n" +
            "FROM \n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday,payRecordTotalAmount\n" +
            "   FROM tb_income \n" +
            "   -- 这里是限制返回最近30天的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{statisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{statisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount FROM tb_payrecord AS d,\n" +
            "   (\n" +
            "   SELECT otherOrderId FROM tb_order AS b,\n" +
            "   ( \n" +
            "   SELECT userLoginTime,userId FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{statisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{statisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{statisticsRequest.endTime},' 23:59:59') AND userName=a.phone\n" +
            "   GROUP BY userLoginTime\n" +
            "   ) AS c\n" +
            "   WHERE b.userId=c.userId\n" +
            "   ) AS e\n" +
            "   WHERE d.outTradeNo=e.otherOrderId AND payRecordStatus='1'\n" +
            "   GROUP BY d.payRecordTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<IncomeResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
