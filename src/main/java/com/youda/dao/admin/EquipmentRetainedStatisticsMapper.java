package com.youda.dao.admin;

import com.youda.response.admin.EquipmentRetainedStatisticsResponse;
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
public interface EquipmentRetainedStatisticsMapper {

    /*实现今天新增设备的统计*/
    @Select("SELECT (today.todayUserRetained/todayRate.todayUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate \n" +
            "FROM     \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user    \n" +
            "        WHERE userLoginTime>=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS todayRate,    \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user \n" +
            "        WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS today")
    List<EquipmentRetainedStatisticsResponse> todayEquipmentStatistics();

    /*实现昨天新增设备的统计*/
    @Select("SELECT (today.todayUserRetained/yestodayRate.yestodayUserRetained)*100 AS userRetainedCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate \n" +
            "FROM     \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS yestodayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user    \n" +
            "        WHERE userLoginTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') AND userLoginTime<=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS yestodayRate,    \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user \n" +
            "        WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS today")
    List<EquipmentRetainedStatisticsResponse> yestodayEquipmentStatistics();

    /*实现一周的新增设备的统计*/
    @Select("SELECT (today.todayUserRetained/yestodayRate.yestodayUserRetained)*100 AS userRetainedCount,DATE_SUB(CURDATE(),INTERVAL 1 WEEK) AS ddate \n" +
            "FROM     \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS yestodayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user    \n" +
            "        WHERE userLoginTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 WEEK),' 00:00:00') AND userLoginTime<=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 WEEK),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS yestodayRate,    \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user \n" +
            "        WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS today\n")
    List<EquipmentRetainedStatisticsResponse> aWeekEquipmentStatistics();

    /*实现一个月的新增设备的统计*/
    @Select("SELECT (today.todayUserRetained/yestodayRate.yestodayUserRetained)*100 AS userRetainedCount,#{endTime} AS ddate \n" +
            "FROM     \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS yestodayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user    \n" +
            "        WHERE userLoginTime>=CONCAT(DATE_SUB(#{beginTime},' 00:00:00') AND userLoginTime<=CONCAT(#{endTime},' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS yestodayRate,    \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user \n" +
            "        WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS today")
    List<EquipmentRetainedStatisticsResponse> aMonthEquipmentStatistics();

    /*实现自定义日期的新增设备的统计*/
    @Select("SELECT (today.todayUserRetained/yestodayRate.yestodayUserRetained)*100 AS userRetainedCount,#{endTime} AS ddate \n" +
            "FROM     \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS yestodayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user    \n" +
            "        WHERE userLoginTime>=CONCAT(DATE_SUB(#{beginTime},' 00:00:00') AND userLoginTime<=CONCAT(#{endTime},' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS yestodayRate,    \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user \n" +
            "        WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS today\n")
    List<EquipmentRetainedStatisticsResponse> customEquipmentStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /*实现全部的新增设备统计*/
    @Select("SELECT (today.todayUserRetained/yestodayRate.yestodayUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate \n" +
            "FROM     \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS yestodayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user    \n" +
            "        WHERE userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS yestodayRate,    \n" +
            "    (\n" +
            "        SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate \n" +
            "        FROM tb_user \n" +
            "        WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "        AND userUseDevice <> NULL OR userUseDevice IS NOT NULL OR userUseDevice != ''\n" +
            "    ) AS today\n")
    List<EquipmentRetainedStatisticsResponse> allEquipmentStatistics();
}
