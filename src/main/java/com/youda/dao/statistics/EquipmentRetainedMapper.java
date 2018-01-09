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
    @Select("SELECT equipmentActive.StatisticsDate AS StatisticsDate,IFNULL((equipmentActive.equipmentActiveCount/equipmentNew.equipmentNewCount)*100,0.0) AS equipmentRate\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(equipmentActiveCount,0) AS equipmentActiveCount        \n" +
            "    FROM         \n" +
            "    (     \n" +
            "        SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS equipmentActiveCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userLoginTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userLoginTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} \n" +
            "        GROUP BY userLoginTime        \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS equipmentActiveCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )    \n" +
            ") AS b      \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS equipmentActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(equipmentNewCount,0) AS equipmentNewCount        \n" +
            "    FROM         \n" +
            "    (        \n" +
            "        SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS equipmentNewCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userRegistedTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userRegistedTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} \n" +
            "        GROUP BY userRegistedTime    \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT DISTINCT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS equipmentNewCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )         \n" +
            ") AS b        \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS equipmentNew\n" +
            "ON equipmentActive.StatisticsDate=equipmentNew.StatisticsDate\n" +
            "GROUP BY equipmentActive.StatisticsDate")
    List<EquipmentRetainedResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现全部的新增设备统计*/
    @Select("SELECT equipmentActive.StatisticsDate AS StatisticsDate,IFNULL((equipmentActive.equipmentActiveCount/equipmentNew.equipmentNewCount)*100,0.0) AS equipmentRate\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(equipmentActiveCount,0) AS equipmentActiveCount        \n" +
            "    FROM         \n" +
            "    (     \n" +
            "        SELECT DISTINCT DATE(userLoginTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS equipmentActiveCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userLoginTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userLoginTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} \n" +
            "        GROUP BY userLoginTime        \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS equipmentActiveCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d')        \n" +
            "    )    \n" +
            ") AS b      \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS equipmentActive\n" +
            "RIGHT JOIN\n" +
            "(\n" +
            "    SELECT DISTINCT DATE_FORMAT(StatisticsDate,'%Y-%m-%d') AS StatisticsDate,IFNULL(equipmentNewCount,0) AS equipmentNewCount        \n" +
            "    FROM         \n" +
            "    (        \n" +
            "        SELECT DISTINCT DATE(userRegistedTime) AS StatisticsDate,        \n" +
            "        COUNT(DISTINCT userId) AS equipmentNewCount         \n" +
            "        FROM tb_user_caculator         \n" +
            "        WHERE userRegistedTime>=DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d') && userRegistedTime<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') AND gameChannelId=#{statisticsRequest.gameChannelId} \n" +
            "        GROUP BY userRegistedTime    \n" +
            "    UNION        \n" +
            "    (        \n" +
            "        SELECT DISTINCT datelist AS StatisticsDate,        \n" +
            "        payRecordTotalAmount AS equipmentNewCount        \n" +
            "        FROM tb_income         \n" +
            "        WHERE DATE_FORMAT(#{statisticsRequest.beginTime},'%Y-%m-%d')<= DATE(datelist)&&DATE(datelist)<=DATE_FORMAT(#{statisticsRequest.endTime},'%Y-%m-%d') \n" +
            "    )         \n" +
            ") AS b        \n" +
            "GROUP BY StatisticsDate\n" +
            ") AS equipmentNew\n" +
            "ON equipmentActive.StatisticsDate=equipmentNew.StatisticsDate\n" +
            "GROUP BY equipmentActive.StatisticsDate")
    List<EquipmentRetainedResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
