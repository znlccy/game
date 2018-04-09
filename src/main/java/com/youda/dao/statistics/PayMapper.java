package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.PayResponse;
import com.youda.response.statistics.StatisticsRateResponse;
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
    @Select("SELECT IFNULL(FORMAT((payPlayerCount.payCount/userActiveCount.userActiveCount),2),0.00) AS StatisticsRate,payPlayerCount.StatisticsDate AS StatisticsDate\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(payCount,0) AS payCount\n" +
            "    FROM      \n" +
            "    (    \n" +
            "        SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,     \n" +
            "        SUM(payRecordTotalAmount) AS incomeTotalMoney,     \n" +
            "        COUNT(DISTINCT payRecordUser) AS payCount     \n" +
            "        FROM tb_payrecord      \n" +
            "        WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice=#{statisticsRequest.userUseDevice}\n" +
            "        GROUP BY DATE(payRecordTime)  \n" +
            "    UNION    \n" +
            "       (     \n" +
            "        SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "        payRecordTotalAmount AS incomeTotalMoney,     \n" +
            "        incomeCount AS payCount     \n" +
            "        FROM tb_income      \n" +
            "        WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            "       )      \n" +
            "    ) AS b     \n" +
            "    GROUP BY StatisticsDate\n" +
            ") AS payPlayerCount\n" +
            "INNER JOIN\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount     \n" +
            "    FROM      \n" +
            "    (  \n" +
            "       SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,     \n" +
            "       COUNT(DISTINCT userId) AS userActiveCount      \n" +
            "       FROM tb_user_caculator      \n" +
            "       WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "       GROUP BY userLoginTime     \n" +
            "    UNION     \n" +
            "    (     \n" +
            "       SELECT datelist AS StatisticsDate,     \n" +
            "       payRecordTotalAmount AS userActiveCount     \n" +
            "       FROM tb_income      \n" +
            "       WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            "    ) \n" +
            "    ) AS b   \n" +
            "    GROUP BY StatisticsDate\n" +
            ") AS userActiveCount\n" +
            "ON payPlayerCount.StatisticsDate=userActiveCount.StatisticsDate")
    List<StatisticsRateResponse> customPayRateTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的付费率统计*/
    @Select("SELECT IFNULL(FORMAT((payPlayerCount.payCount/userActiveCount.userActiveCount),2),0.00) AS StatisticsRate,payPlayerCount.StatisticsDate AS StatisticsDate\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(payCount,0) AS payCount\n" +
            "    FROM      \n" +
            "    (    \n" +
            "        SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,     \n" +
            "        SUM(payRecordTotalAmount) AS incomeTotalMoney,     \n" +
            "        COUNT(DISTINCT payRecordUser) AS payCount     \n" +
            "        FROM tb_payrecord      \n" +
            "        WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice IS NOT NULL \n" +
            "        GROUP BY DATE(payRecordTime)  \n" +
            "    UNION    \n" +
            "       (     \n" +
            "        SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "        payRecordTotalAmount AS incomeTotalMoney,     \n" +
            "        incomeCount AS payCount     \n" +
            "        FROM tb_income      \n" +
            "        WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            "       )      \n" +
            "    ) AS b     \n" +
            "    GROUP BY StatisticsDate\n" +
            ") AS payPlayerCount\n" +
            "INNER JOIN\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount     \n" +
            "    FROM      \n" +
            "    (  \n" +
            "       SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,     \n" +
            "       COUNT(DISTINCT userId) AS userActiveCount      \n" +
            "       FROM tb_user_caculator      \n" +
            "       WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL \n" +
            "       GROUP BY userLoginTime     \n" +
            "    UNION     \n" +
            "    (     \n" +
            "       SELECT datelist AS StatisticsDate,     \n" +
            "       payRecordTotalAmount AS userActiveCount     \n" +
            "       FROM tb_income      \n" +
            "       WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            "    ) \n" +
            "    ) AS b   \n" +
            "    GROUP BY StatisticsDate\n" +
            ") AS userActiveCount\n" +
            "ON payPlayerCount.StatisticsDate=userActiveCount.StatisticsDate")
    List<StatisticsRateResponse> allPayRate(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现自定义的ARPU的统计*/
    @Select("SELECT IFNULL(FORMAT((payIncomeCount.incomeTotalMoney/(userActiveCount.userActiveCount)*100),2),0.00) AS StatisticsRate,payIncomeCount.StatisticsDate AS StatisticsDate    \n" +
            " FROM    \n" +
            " (    \n" +
            "     SELECT        \n" +
            "     DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,         \n" +
            "     IFNULL(incomeCount,0) AS incomeCount,     \n" +
            "     IFNULL(FORMAT(incomeTotalMoney,2),0.00) AS incomeTotalMoney        \n" +
            "     FROM          \n" +
            "     (        \n" +
            "         SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,         \n" +
            "         SUM(payRecordTotalAmount) AS incomeTotalMoney,         \n" +
            "         COUNT(DISTINCT payRecordTime) AS incomeCount         \n" +
            "         FROM tb_payrecord          \n" +
            "         WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice IS NOT NULL     \n" +
            "         GROUP BY DATE(payRecordTime)      \n" +
            "     UNION        \n" +
            "        (         \n" +
            "         SELECT DISTINCT datelist AS StatisticsDate,         \n" +
            "         payRecordTotalAmount AS incomeTotalMoney,         \n" +
            "         incomeCount AS incomeCount         \n" +
            "         FROM tb_income          \n" +
            "         WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')     \n" +
            "        )          \n" +
            "     ) AS b         \n" +
            "     GROUP BY StatisticsDate    \n" +
            " ) AS payIncomeCount    \n" +
            " INNER JOIN    \n" +
            " (    \n" +
            "     SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount         \n" +
            "     FROM          \n" +
            "     (      \n" +
            "\t SELECT DATE(b.userLoginTime) AS StatisticsDate,SUM(b.userActiveCount) AS userActiveCount\n" +
            "\t FROM  (SELECT COUNT(DISTINCT(userId)) AS userActiveCount,userLoginTime,gameChannelId,userUseDevice\n" +
            "\t FROM tb_user_caculator GROUP BY userLoginTime) AS b \n" +
            "\t WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime <=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND b.gameChannelId=#{statisticsRequest.gameChannelId} AND b.userUseDevice IS NOT NULL\n" +
            "\t GROUP BY DATE_FORMAT(b.userLoginTime, '%Y-%m-%d')          \n" +
            "     UNION         \n" +
            "     (         \n" +
            "        SELECT datelist AS StatisticsDate,         \n" +
            "        payRecordTotalAmount AS userActiveCount         \n" +
            "        FROM tb_income          \n" +
            "        WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')      \n" +
            "     )     \n" +
            "     ) AS b       \n" +
            "     GROUP BY StatisticsDate    \n" +
            " ) AS userActiveCount    \n" +
            " ON payIncomeCount.StatisticsDate=userActiveCount.StatisticsDate")
    List<StatisticsRateResponse> customArpuTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的ARPU统计*/
    @Select("SELECT IFNULL(FORMAT((payIncomeCount.incomeTotalMoney/(userActiveCount.userActiveCount)*100),2),0.00) AS StatisticsRate,payIncomeCount.StatisticsDate AS StatisticsDate    \n" +
            " FROM    \n" +
            " (    \n" +
            "     SELECT        \n" +
            "     DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,         \n" +
            "     IFNULL(incomeCount,0) AS incomeCount,     \n" +
            "     IFNULL(FORMAT(incomeTotalMoney,2),0.00) AS incomeTotalMoney        \n" +
            "     FROM          \n" +
            "     (        \n" +
            "         SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,         \n" +
            "         SUM(payRecordTotalAmount) AS incomeTotalMoney,         \n" +
            "         COUNT(DISTINCT payRecordTime) AS incomeCount         \n" +
            "         FROM tb_payrecord          \n" +
            "         WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice IS NOT NULL     \n" +
            "         GROUP BY DATE(payRecordTime)      \n" +
            "     UNION        \n" +
            "        (         \n" +
            "         SELECT DISTINCT datelist AS StatisticsDate,         \n" +
            "         payRecordTotalAmount AS incomeTotalMoney,         \n" +
            "         incomeCount AS incomeCount         \n" +
            "         FROM tb_income          \n" +
            "         WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')     \n" +
            "        )          \n" +
            "     ) AS b         \n" +
            "     GROUP BY StatisticsDate    \n" +
            " ) AS payIncomeCount    \n" +
            " INNER JOIN    \n" +
            " (    \n" +
            "     SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(userActiveCount,0) AS userActiveCount         \n" +
            "     FROM          \n" +
            "     (      \n" +
            "\t SELECT DATE(b.userLoginTime) AS StatisticsDate,SUM(b.userActiveCount) AS userActiveCount\n" +
            "\t FROM  (SELECT COUNT(DISTINCT(userId)) AS userActiveCount,userLoginTime,gameChannelId,userUseDevice\n" +
            "\t FROM tb_user_caculator GROUP BY userLoginTime) AS b \n" +
            "\t WHERE userLoginTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && userLoginTime <=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND b.gameChannelId=#{statisticsRequest.gameChannelId} AND b.userUseDevice IS NOT NULL\n" +
            "\t GROUP BY DATE_FORMAT(b.userLoginTime, '%Y-%m-%d')          \n" +
            "     UNION         \n" +
            "     (         \n" +
            "        SELECT datelist AS StatisticsDate,         \n" +
            "        payRecordTotalAmount AS userActiveCount         \n" +
            "        FROM tb_income          \n" +
            "        WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s')      \n" +
            "     )     \n" +
            "     ) AS b       \n" +
            "     GROUP BY StatisticsDate    \n" +
            " ) AS userActiveCount    \n" +
            " ON payIncomeCount.StatisticsDate=userActiveCount.StatisticsDate")
    List<StatisticsRateResponse> allArpu(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现自定义日期的ARPPU统计*/
    @Select("SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,         \n" +
            "CONVERT((CONVERT(SUM(payRecordTotalAmount),DECIMAL(10,2)))/(COUNT(DISTINCT payRecordUser)),DECIMAL(10,2))  AS StatisticsRate       \n" +
            "FROM tb_payrecord          \n" +
            "WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice IS NOT NULL     \n" +
            "GROUP BY DATE(payRecordTime) ")
    List<StatisticsRateResponse> customArppuTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部ARPPU统计*/
    @Select("SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,         \n" +
            "CONVERT((CONVERT(SUM(payRecordTotalAmount),DECIMAL(10,2)))/(COUNT(DISTINCT payRecordUser)),DECIMAL(10,2))  AS StatisticsRate       \n" +
            "FROM tb_payrecord          \n" +
            "WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice IS NOT NULL     \n" +
            "GROUP BY DATE(payRecordTime)")
    List<StatisticsRateResponse> allArppu(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现任意日期支付玩家统计*/
    @Select("SELECT    \n" +
            "     DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,     \n" +
            "     IFNULL(payCount,0) AS payCount\n" +
            "FROM      \n" +
            "(    \n" +
            "    SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,     \n" +
            "    SUM(payRecordTotalAmount) AS incomeTotalMoney,     \n" +
            "    COUNT(DISTINCT payRecordUser) AS payCount     \n" +
            "    FROM tb_payrecord      \n" +
            "    WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice=#{statisticsRequest.userUseDevice} \n" +
            "    GROUP BY DATE(payRecordTime)  \n" +
            "UNION    \n" +
            "   (     \n" +
            "    SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "    payRecordTotalAmount AS incomeTotalMoney,     \n" +
            "    incomeCount AS payCount     \n" +
            "    FROM tb_income      \n" +
            "    WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            "   )      \n" +
            ") AS b     \n" +
            "GROUP BY StatisticsDate")
    List<PayResponse> customPayPlayersTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的付费玩家统计*/
    @Select("SELECT    \n" +
            "     DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,     \n" +
            "     IFNULL(payCount,0) AS payCount\n" +
            "FROM      \n" +
            "(    \n" +
            "    SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,     \n" +
            "    SUM(payRecordTotalAmount) AS incomeTotalMoney,     \n" +
            "    COUNT(DISTINCT payRecordUser) AS payCount     \n" +
            "    FROM tb_payrecord      \n" +
            "    WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND userUseDevice IS NOT NULL \n" +
            "    GROUP BY DATE(payRecordTime)  \n" +
            "UNION    \n" +
            "   (     \n" +
            "    SELECT DISTINCT datelist AS StatisticsDate,     \n" +
            "    payRecordTotalAmount AS incomeTotalMoney,     \n" +
            "    incomeCount AS payCount     \n" +
            "    FROM tb_income      \n" +
            "    WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            "   )      \n" +
            ") AS b     \n" +
            "GROUP BY StatisticsDate")
    List<PayResponse> allPayPlayers(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
