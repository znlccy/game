package com.youda.dao.admin;

import com.youda.response.admin.PayStatisticsResponse;
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
public interface PayStatisticsMapper {

    /*实现今天付费率统计*/
    @Select("SELECT (todayPayPlayer.payCount/todayActiveUser.activeUserCount)*100 AS payCount,CURDATE() AS ddate,payMoneyTotal FROM \n" +
            "(SELECT COUNT(*) AS payCount,CURDATE() AS ddate,SUM(payRecordTotalAmount) AS payMoneyTotal FROM tb_payrecord \n" +
            "WHERE payRecordTime>=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND payRecordTime <=CONCAT(CURDATE(),' 24:00:00')\n" +
            "AND payRecordStatus='1') AS todayPayPlayer,\n" +
            "(SELECT COUNT(*) AS activeUserCount,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >= CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime <=CONCAT(CURDATE(), ' 24:00:00')) AS todayActiveUser")
    List<PayStatisticsResponse> todayPayRateStatistics();

    /*实现昨天的付费率统计*/
    @Select("SELECT (yestodayPayPlayer.payCount/yestodayActiveUser.activeUserCount)*100 AS payCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate,payMoneyTotal FROM \n" +
            "(SELECT COUNT(*) AS payCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate,SUM(payRecordTotalAmount) AS payMoneyTotal FROM tb_payrecord \n" +
            "WHERE payRecordTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') \n" +
            "AND payRecordTime <=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00')\n" +
            "AND payRecordStatus='1') AS yestodayPayPlayer,\n" +
            "(SELECT COUNT(*) AS activeUserCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate FROM tb_user WHERE userLoginTime >= CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') \n" +
            "AND userLoginTime <=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY), ' 24:00:00')) AS yestodayActiveUser")
    List<PayStatisticsResponse> yestodayPayRateStatistics();

    /*实现一周的付费率统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekPayRateStatistics();

    /*实现一个月付费率统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthPayRateStatistics();

    /*实现自定义日期付费率统计*/
    @Select("")
    List<PayStatisticsResponse> customPayRateStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今天的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> todayArpuStatistics();

    /*实现昨天的ARPPU的统计*/
    @Select("")
    List<PayStatisticsResponse> yestodayArpuStatistics();

    /*实现一周的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekArpuStatistics();

    /*实现一个月的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthArpuStatistics();

    /*实现自定义的ARPU的统计*/
    @Select("")
    List<PayStatisticsResponse> customArpuStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今天的ARPPU的统计*/
    @Select("")
    List<PayStatisticsResponse> todayArppuStatistics();

    /*实现昨天的ARPPU的统计*/
    @Select("")
    List<PayStatisticsResponse> yestodayArppuStatistics();

    /*实现一周的ARPPU统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekArppuStatistics();

    /*实现一个月的ARPPU统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthArppuStatistics();

    /*实现自定义日期的ARPPU统计*/
    @Select("")
    List<PayStatisticsResponse> customArppuStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今天的支付玩家统计*/
    @Select("SELECT COUNT(*) AS payCount,CURDATE() AS ddate,SUM(payRecordTotalAmount) AS payMoneyTotal FROM tb_payrecord \n" +
            "WHERE payRecordTime>=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND payRecordTime <=CONCAT(CURDATE(),' 24:00:00')\n" +
            "AND payRecordStatus='1' ")
    List<PayStatisticsResponse> todayPayingPlayersStatistics();

    /*实现昨天的支付玩家统计*/
    @Select("SELECT COUNT(*) AS payCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate,SUM(payRecordTotalAmount) AS payMoneyTotal FROM tb_payrecord \n" +
            "WHERE payRecordTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') \n" +
            "AND payRecordTime <=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00')\n" +
            "AND payRecordStatus='1'")
    List<PayStatisticsResponse> yestodayPayingPlayersStatistics();

    /*实现一周支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> aWeekPayingPlayersStatistics();

    /*实现一个月支付玩家统计*/
    @Select("")
    List<PayStatisticsResponse> aMonthPayingPlayersStatistics();

    /*实现任意日期支付玩家统计*/
    @Select("s")
    List<PayStatisticsResponse> customPayingPlayersStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

}
