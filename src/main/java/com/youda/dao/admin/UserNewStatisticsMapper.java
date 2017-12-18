package com.youda.dao.admin;

import com.youda.response.admin.UserStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserNewStatisticsMapper {

    /*实现新增用户或者账户的统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= DATE(datelist)&&DATE(datelist)<=CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userRegisteredTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userRegisteredTime>=DATE_SUB(CURDATE(), INTERVAL 1 MONTH) && userRegisteredTime<=CURRENT_TIMESTAMP\n" +
            "            GROUP BY userRegisteredTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserStatisticsResponse> NearlyAMonthNewUserStatistics();

    /*实现自定义日期查询*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回自定义日期的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  #{beginTime} <= DATE(datelist)&&DATE(datelist)<=concat(#{endTime},'24:00:00')\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userRegisteredTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userRegisteredTime>=#{beginTime} && userRegisteredTime<=concat(#{endTime},'24:00:00')\n" +
            "            GROUP BY userRegisteredTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserStatisticsResponse> cudtomDateNewUserStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现今日新增用户的统计*/
    @Select("SELECT COUNT(*) AS newUserCount,CURDATE() AS ddate FROM tb_user WHERE userRegisteredTime=CURDATE()")
    List<UserStatisticsResponse> todayNewUserStatistics();

    /*实现昨日新增用户的统计*/
    @Select("SELECT COUNT(*) AS newUserCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate FROM tb_user WHERE userRegisteredTime=DATE_SUB(CURDATE(),INTERVAL 1 DAY)")
    List<UserStatisticsResponse> yestodayNewUserStatistics();

    /*实现每周新增用户的统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近一周的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= DATE(datelist)&&DATE(datelist)<=CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userRegisteredTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userRegisteredTime>=DATE_SUB(CURDATE(), INTERVAL 1 WEEK) && userRegisteredTime<=CURRENT_TIMESTAMP\n" +
            "            GROUP BY userRegisteredTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserStatisticsResponse> everyWeekNewUserStatistics();

    /*实现查询所有新增用户统计，必须实现分页效果*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回所有的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE(datelist)&&DATE(datelist)<CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userRegisteredTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE userRegisteredTime<=CURDATE() \n" +
            "            GROUP BY userRegisteredTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserStatisticsResponse> allNewUserStatistics();
}
