package com.youda.dao.admin;

import com.youda.response.admin.UserActiveStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/19 16:30
 * @Version 1.0.0
 * @Instructions 定义用户活跃数统计的Dao层
 */

@Mapper
public interface UserActiveStatisticsMapper {

    /*实现今天活跃用户统计*/
    @Select("SELECT COUNT(*) AS userActiveCount,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >= CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime <=CONCAT(CURDATE(), ' 24:00:00');")
    List<UserActiveStatisticsResponse> todayActiveUserStatistics();

    /*实现昨天活跃用户统计*/
    @Select("SELECT COUNT(*) AS userActiveCount,DATE_SUB(CURDATE(),INTERVAL 1 DAY) AS ddate FROM tb_user WHERE userLoginTime >= CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY),' 00:00:00') \n" +
            "AND userLoginTime <=CONCAT(DATE_SUB(CURDATE(),INTERVAL 1 DAY), ' 24:00:00');\n")
    List<UserActiveStatisticsResponse> yestodayActiveUserStatistics();

    /*实现每周活跃用户统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  DATE_SUB(CURDATE(), INTERVAL 1 WEEK) <= DATE(datelist)&&DATE(datelist)<=CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userLoginTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userLoginTime>=DATE_SUB(CURDATE(), INTERVAL 1 WEEK) && userLoginTime<=CURRENT_TIMESTAMP\n" +
            "            GROUP BY userLoginTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserActiveStatisticsResponse> aWeekActiveUserStatistics();

    /*实现一个月活跃用户统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
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
            "                userLoginTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userLoginTime>=DATE_SUB(CURDATE(), INTERVAL 1 MONTH) && userLoginTime<=CURRENT_TIMESTAMP\n" +
            "            GROUP BY userLoginTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserActiveStatisticsResponse> aMonthActiveUserStatistics();

    /*实现自选日期活跃用户统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE  #{beginTime} <= DATE(datelist)&&DATE(datelist)<=concat(#{endTime},'24:00:00')\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userLoginTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userLoginTime>=#{beginTime} && userLoginTime<=concat(#{endTime},'24:00:00')\n" +
            "            GROUP BY userLoginTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserActiveStatisticsResponse> customDateActiveUserStatistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /*实现所有活跃用户统计*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS userActiveCount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT\n" +
            "            datelist AS dday\n" +
            "        FROM\n" +
            "            tb_calendar \n" +
            "            -- 这里是限制返回最近30天的数据\n" +
            "            -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "            WHERE DATE(datelist)&&DATE(datelist)<CURRENT_TIMESTAMP\n" +
            "        UNION ALL\n" +
            "            SELECT\n" +
            "                userLoginTime\n" +
            "            FROM\n" +
            "                tb_user\n" +
            "            WHERE  userLoginTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "            GROUP BY userLoginTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserActiveStatisticsResponse> allActiveUserStatistics();
}
