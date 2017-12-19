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

    /*实现今天的用户相对于昨天留存统计*/
    @Select("SELECT (today.todayUserRetained/yestoday.yestodayUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate FROM \n" +
            "(SELECT COUNT(*) AS yestodayUserRetained,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate FROM tb_user WHERE userLoginTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00')\n" +
            " AND userLoginTime<=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00')) AS yestoday,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today")
    List<UserRetainedStatisticsResponse> todayUserRetainedStatistics();

    /*实现昨天的用户留存统计*/
    @Select("SELECT (yestodayRate.yestodayRetained/beforeYestodayRate.beforeYestodayRetained)*100 AS userRetainedCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate FROM \n" +
            "(SELECT COUNT(*) AS beforeYestodayRetained,DATE_SUB(CURDATE(),INTERVAL 2 DAY) AS ddate FROM tb_user \n" +
            "WHERE userLoginTime >= CONCAT(DATE_SUB(CURDATE(),INTERVAL 2 DAY),' 00:00:00')\n" +
            "AND userLoginTime <= CONCAT(DATE_SUB(CURDATE(),INTERVAL 2 DAY),' 24:00:00')) AS beforeYestodayRate,\n" +
            " (SELECT COUNT(*) AS yestodayRetained,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate FROM tb_user \n" +
            "WHERE userLoginTime >= CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00')\n" +
            "AND userLoginTime <= CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 24:00:00')) AS yestodayRate")
    List<UserRetainedStatisticsResponse> yestodayUserRetainedStatistics();

    /*实现一周的用户留存统计*/
    @Select("SELECT (today.todayUserRetained/weekrate.weekUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate FROM \n" +
            "(SELECT COUNT(*) AS weekUserRetained,DATE_SUB(CURDATE(),INTERVAL 1 WEEK) AS ddate FROM tb_user\n" +
            "WHERE userLoginTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 WEEK),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS weekrate,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today")
    List<UserRetainedStatisticsResponse> aWeekUserRetainedStatistics();

    /*实现一月的用户留存统计*/
    @Select("SELECT (today.todayUserRetained/monthrate.monthUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate FROM \n" +
            "(SELECT COUNT(*) AS monthUserRetained,DATE_SUB(CURDATE(),INTERVAL 1 MONTH) AS ddate FROM tb_user\n" +
            "WHERE userLoginTime>=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 MONTH),' 00:00:00') AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS monthrate,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today")
    List<UserRetainedStatisticsResponse> aMonthUserRetainedStatistics();

    /*实现自定义日期用户留存统计*/
    @Select("SELECT (today.todayUserRetained/customrate.customUserRetained)*100 AS userRetainedCount,#{endTime} AS ddate FROM \n" +
            "(SELECT COUNT(*) AS customUserRetained,CURDATE() AS ddate FROM tb_user\n" +
            "WHERE userLoginTime>=CONCAT(#{beginTime},' 00:00:00') AND userLoginTime<=CONCAT(#{endTime},' 24:00:00')) AS customrate,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today")
    List<UserRetainedStatisticsResponse> customDateRetainedStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现查询所有用户留存的统计*/
    @Select("SELECT (today.todayUserRetained/allrate.allUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate FROM \n" +
            "(SELECT COUNT(*) AS allUserRetained, CURDATE() AS ddate FROM tb_user WHERE userLoginTime <> NULL) AS allrate,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today\n")
    List<UserRetainedStatisticsResponse> allUserRetainedStatisticsStatistics();
}
