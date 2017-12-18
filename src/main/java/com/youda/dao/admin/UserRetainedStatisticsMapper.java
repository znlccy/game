package com.youda.dao.admin;

import com.youda.response.admin.UserNewStatisticsResponse;
import com.youda.response.admin.UserRetainedStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:36
 * @Version 1.0.0
 * @Instructions 实现用户留存Dao层接口
 */

@Mapper
public interface UserRetainedStatisticsMapper {

    /*实现今天的用户留存统计*/
    @Select("select * from tb_user")
    List<UserRetainedStatisticsResponse> todayUserRetainedStatistics();

    /*实现昨天的用户留存统计*/
    @Select("select * from tb_user")
    List<UserRetainedStatisticsResponse> yestodayUserRetainedStatistics();

    /*实现一周的用户留存统计*/
    @Select("select * from tb_user")
    List<UserRetainedStatisticsResponse> aWeekUserRetainedStatistics();

    /*实现一月的用户留存统计*/
    @Select("select * from tb_user")
    List<UserRetainedStatisticsResponse> aMonthUserRetainedStatistics();

    /*实现自定义日期用户留存统计*/
    @Select("select * from tb_user")
    List<UserRetainedStatisticsResponse> customDateRetainedStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现查询所有用户留存的统计*/
    @Select("select * from tb_user")
    List<UserRetainedStatisticsResponse> allUserRetainedStatisticsStatistics();
}
