package com.youda.dao.admin;

import com.youda.response.admin.PayStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
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

    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> dayPayRateStatistics();

    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> dayArpuStatistics();

    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> dayArppuStatistics();
}
