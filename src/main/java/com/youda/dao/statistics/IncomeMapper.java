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
    @Select("SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(incomeCount,0) AS incomeCount,IFNULL(FORMAT(incomeTotalMoney,2),0.00) AS \n" +
            "incomeTotalMoney FROM \n" +
            "(SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,SUM(payRecordTotalAmount) AS incomeTotalMoney,COUNT(DISTINCT payRecordTime) AS incomeCount FROM \n" +
            "tb_payrecord \n" +
            "WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#\n" +
            "{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND payRecordStatus='1' AND \n" +
            "userUseDevice=#{statisticsRequest.userUseDevice} GROUP BY DATE(payRecordTime)\n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS incomeTotalMoney,incomeCount AS incomeCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#\n" +
            "{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate")
    List<IncomeResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*定义全部的收入统计*/
    @Select("SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(incomeCount,0) AS incomeCount,IFNULL(FORMAT(incomeTotalMoney,2),0.00) AS incomeTotalMoney FROM \n" +
            "( SELECT DISTINCT DATE(payRecordTime) AS StatisticsDate,SUM(payRecordTotalAmount) AS incomeTotalMoney,COUNT(DISTINCT payRecordTime) AS incomeCount FROM tb_payrecord \n" +
            "WHERE payRecordTime>=DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s') && payRecordTime<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') AND gameChannelId=#{statisticsRequest.gameChannelId} AND userUseDevice IS NOT NULL AND payRecordStatus='1' GROUP BY DATE(payRecordTime) \n" +
            "UNION \n" +
            "(SELECT DISTINCT datelist AS StatisticsDate,payRecordTotalAmount AS incomeTotalMoney,incomeCount AS incomeCount FROM tb_income \n" +
            "WHERE DATE_FORMAT(CONCAT(#{statisticsRequest.beginTime},' 00:00:00'),'%Y-%m-%d %H:%i:%s')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(CONCAT(#{statisticsRequest.endTime},' 23:59:59'),'%Y-%m-%d %H:%i:%s') \n" +
            ")) AS b GROUP BY StatisticsDate")
    List<IncomeResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
