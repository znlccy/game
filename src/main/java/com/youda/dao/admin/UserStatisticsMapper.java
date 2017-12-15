package com.youda.dao.admin;

import com.youda.response.admin.UserStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserStatisticsMapper {

    /*实现新增用户或者账户的统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 1 AS newUserCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= DATE(datelist)&&DATE(datelist)<=CURDATE() \n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userRegisteredTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserStatisticsResponse> NearlyAMonthNewUserStatistics();

    /*实现自定义日期查询*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 1 AS newUserCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  #{beginTime} <= DATE(datelist)&&DATE(datelist)<=#{endTime}\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userRegisteredTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userRegisteredTime>=#{beginTime} && userRegisteredTime<='2017-12-14 24:00:00'\n" +
            "    ) a\n" +
            "GROUP BY ddate\n")
    List<UserStatisticsResponse> definitionDateNewUserStatistics(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /*实现每日新增用户的统计*/
    @Select("select count(*) from tb_user group by date_format(now(),'%D')")
    List<UserStatisticsResponse> everyDayNewUserStatistics();

    /*实现每周新增用户的统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 1 AS newUserCount\n" +
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
            "            WHERE  userRegisteredTime>DATE_SUB(CURDATE(), INTERVAL 1 WEEK) && userRegisteredTime<=CURRENT_TIMESTAMP \n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserStatisticsResponse> everyWeekNewUserStatistics();

    /*实现每月新增用户的统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 1 AS newUserCount\n" +
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
            "    ) a\n" +
            "GROUP BY ddate")
    void everyMonthNewUserStatistics();

}
