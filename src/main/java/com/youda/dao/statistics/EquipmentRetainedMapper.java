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
    List<EquipmentRetainedResponse> customTime(@Param("statisticsRequest")StatisticsRequest statisticsRequest);

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
    List<EquipmentRetainedResponse> all(@Param("statisticsRequest")StatisticsRequest statisticsRequest);
}
