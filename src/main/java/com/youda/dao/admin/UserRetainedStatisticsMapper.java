package com.youda.dao.admin;

import com.youda.response.admin.UserNewStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Chencongye
 * @Date 2017/12/15 15:36
 * @Version 1.0.0
 * @Instructions 实现用户留存Dao层接口
 */

@Mapper
public interface UserRetainedStatisticsMapper {

    /*实现今天的用户留存统计*/
    UserNewStatisticsResponse todayUserRetainedStatistics();

    /*实现昨天的用户留存统计*/
    UserNewStatisticsResponse yestodayUserRetainedStatistics();

    /*实现一周的用户留存统计*/
    UserNewStatisticsResponse aWeekUserRetained();

    /*实现一月的用户留存统计*/
    UserNewStatisticsResponse aMonthUserRetained();

    /*实现自定义日期用户留存统计*/
    UserNewStatisticsResponse customDateRetained();

}
