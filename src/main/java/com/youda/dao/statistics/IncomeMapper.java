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
    List<IncomeResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

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
    List<IncomeResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
