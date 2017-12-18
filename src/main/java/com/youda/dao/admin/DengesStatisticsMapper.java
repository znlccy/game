package com.youda.dao.admin;

import com.youda.response.admin.PayStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:37
 * @Version 1.0.0
 * @Instructions 实现当日的统计Dao层
 */

@Mapper
public interface DengesStatisticsMapper {

    /*实现日付费率统计的功能*/
    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> dengesPayRate();

    /*实现日ARPU的统计的功能*/
    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> dengesARPU();

    /*实现日ARPPU的统计的功能*/
    @Select("select * from tb_payrecord")
    List<PayStatisticsResponse> dengesARPPU();

}
