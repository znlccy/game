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
    @Select("select * from tb_user")
    List<EquipmentStatisticsResponse> todayEquipmentStatistics();

    /*实现昨天新增设备的统计*/
    @Select("select * from tb_user")
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
