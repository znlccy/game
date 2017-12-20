package com.youda.dao.admin;

import com.youda.response.admin.IncomeStatisticsResponse;
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
public interface IncomeStatisticsMapper {

    /*定义今天的收入统计*/
    @Select("SELECT CURDATE() AS ddate,SUM(payRecordTotalAmount) AS incomeTotalMoney,COUNT(*) AS incomeCount\n" +
            "FROM tb_payrecord\n" +
            "WHERE payRecordTime >=CONCAT(CURDATE(),' 00:00:00') AND payRecordTime<=CONCAT(CURDATE(),' 24:00:00') AND payRecordStatus='1' ")
    List<IncomeStatisticsResponse> todayIncomeStatistics();

    /*定义昨天的收入统计*/
    @Select("SELECT DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate,SUM(payRecordTotalAmount) AS incomeTotalMoney,COUNT(*) AS incomeCount\n" +
            "FROM tb_payrecord\n" +
            "WHERE payRecordTime >=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') \n" +
            "AND payRecordTime<=payRecordTime >=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00') \n" +
            "AND payRecordStatus='1'")
    List<IncomeStatisticsResponse> yestodayIncomeStatistics();

    /*定义一周的收入统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS incomeCount,\n" +
            "    SUM(payRecordTotalAmount) AS incomeTotalMoney\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday,payRecordTotalAmount\n" +
            "        FROM\n" +
            "            tb_income \n" +
            "            -- 这里是限制返回最近一周的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= DATE(datelist)&&DATE(datelist)<=CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount\n" +
            "            FROM \n" +
            "                tb_payrecord\n" +
            "            WHERE  payRecordTime>=DATE_SUB(CURDATE(), INTERVAL 1 WEEK) && payRecordTime<=CURRENT_TIMESTAMP\n" +
            "            GROUP BY payRecordTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<IncomeStatisticsResponse> aWeekIncomeStatistics();

    /*定义一个月的收入统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS incomeCount,\n" +
            "    SUM(payRecordTotalAmount) AS incomeTotalMoney\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday,payRecordTotalAmount\n" +
            "        FROM\n" +
            "            tb_income \n" +
            "            -- 这里是限制返回最近一周的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= DATE(datelist)&&DATE(datelist)<=CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount\n" +
            "            FROM \n" +
            "                tb_payrecord\n" +
            "            WHERE  payRecordTime>=DATE_SUB(CURDATE(), INTERVAL 1 MONTH) && payRecordTime<=CURRENT_TIMESTAMP\n" +
            "            GROUP BY payRecordTime\n" +
            "    ) a\n" +
            "GROUP BY ddate\n")
    List<IncomeStatisticsResponse> aMonthIncomeStatistics();

    /*定义任意日期的收入统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS incomeCount,\n" +
            "    SUM(payRecordTotalAmount) AS incomeTotalMoney\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday,payRecordTotalAmount\n" +
            "        FROM\n" +
            "            tb_income \n" +
            "            -- 这里是限制返回最近一周的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  CONCAT(#{beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{endTime},' 24:00:00')\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount\n" +
            "            FROM \n" +
            "                tb_payrecord\n" +
            "            WHERE  payRecordTime>=CONCAT(#{beginTime},' 00:00:00') && payRecordTime<=CONCAT(#{endTime},' 24:00:00')\n" +
            "            GROUP BY payRecordTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<IncomeStatisticsResponse> customIncomeStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /*定义全部的收入统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS incomeCount,\n" +
            "    SUM(payRecordTotalAmount) AS incomeTotalMoney\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday,payRecordTotalAmount\n" +
            "        FROM\n" +
            "            tb_income \n" +
            "            -- 这里是限制返回最近一周的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE DATE(datelist)&&DATE(datelist)<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                payRecordTime,SUM(payRecordTotalAmount) AS payRecordTotalAmount\n" +
            "            FROM \n" +
            "                tb_payrecord\n" +
            "            WHERE  payRecordTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "            GROUP BY payRecordTime\n" +
            "    ) a\n" +
            "GROUP BY ddate\n")
    List<IncomeStatisticsResponse> allIncomeStatistics();
}
