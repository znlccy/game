package com.youda.dao.admin;

import com.youda.response.admin.EquipmentStatisticsResponse;
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
public interface EquipmentStatisticsMapper {

    /*实现今天新增设备的统计*/
    @Select("SELECT DATE(dday) ddate,COUNT(*) -2 AS newEquipmentCount     \n" +
            "FROM       \n" +
            "(       \n" +
            "    SELECT datelist AS dday    \n" +
            "    FROM       \n" +
            "    tb_calendar        \n" +
            "    -- 这里是限制返回最近一天的数据             \n" +
            "    WHERE CONCAT(CURDATE(),' 00:00:00')<= DATE(datelist)&&DATE(datelist)<=CONCAT(CURDATE(),' 24:00:00')      \n" +
            "    UNION ALL       \n" +
            "    SELECT userLoginTime    \n" +
            "    FROM        \n" +
            "    tb_user       \n" +
            "    WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00') AND userUseDevice <> NULL OR userUseDevice <> ''      \n" +
            "    GROUP BY userLoginTime       \n" +
            "    ) a       \n" +
            "GROUP BY ddate")
    List<EquipmentStatisticsResponse> todayEquipmentStatistics();

    /*实现昨天新增设备的统计*/
    @Select("SELECT DATE(dday) ddate,COUNT(*) -2 AS newEquipmentCount     \n" +
            "FROM       \n" +
            "(       \n" +
            "    SELECT datelist AS dday    \n" +
            "    FROM       \n" +
            "    tb_calendar        \n" +
            "    -- 这里是限制返回最近一天的数据             \n" +
            "    WHERE CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00')<= DATE(datelist)&&DATE(datelist)<=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00')      \n" +
            "    UNION ALL       \n" +
            "    SELECT userLoginTime    \n" +
            "    FROM        \n" +
            "    tb_user       \n" +
            "    WHERE userLoginTime >=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') AND userLoginTime<=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00') AND userUseDevice <> NULL OR userUseDevice <> ''      \n" +
            "    GROUP BY userLoginTime       \n" +
            "    ) a       \n" +
            "GROUP BY ddate")
    List<EquipmentStatisticsResponse> yestodayEquipmentStatistics();

    /*实现一周的新增设备的统计*/
    @Select("select * from tb_user")
    List<EquipmentStatisticsResponse> aWeekEquipmentStatistics();

    /*实现一个月的新增设备的统计*/
    @Select("select * from tb_user")
    List<EquipmentStatisticsResponse> aMonthEquipmentStatistics();

    /*实现自定义日期的新增设备的统计*/
    @Select("select * from tb_user")
    List<EquipmentStatisticsResponse> customEquipmentStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现全部的新增设备统计*/
    @Select("select * from tb_user")
    List<EquipmentStatisticsResponse> allEquipmentStatistics();
}
