package com.youda.dao.admin;

import com.youda.request.admin.NewUserStatisticsRequest;
import com.youda.response.admin.UserNewStatisticsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.annotations.Parameter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Mapper
public interface UserNewStatisticsMapper {

    /*实现自定义日期查询*/
    @Select("SELECT\n" +
            "    DATE(dday) ddate,\n" +
            "    COUNT(*) - 2 AS newUserCount\n" +
            "FROM\n" +
            "(\n" +
            "(\n" +
            "   SELECT datelist AS dday\n" +
            "   FROM tb_calendar \n" +
            "   -- 这里是限制返回自定义日期的数据\n" +
            "   -- where  DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= date(datelist)&&date(datelist)<=CURDATE() \n" +
            "   WHERE  CONCAT(#{newUserStatisticsRequest.beginTime},' 00:00:00') <= DATE(datelist)&&DATE(datelist)<=CONCAT(#{newUserStatisticsRequest.endTime},' 23:59:59')\n" +
            ")\n" +
            "UNION ALL\n" +
            "(\n" +
            "   SELECT userLoginTime FROM tb_user ,(\n" +
            "   SELECT phone FROM tb_channel_user WHERE channelId IN \n" +
            "   (SELECT channelId FROM tb_gamechannel WHERE gameId IN (SELECT gameId FROM tb_game WHERE gameName=#{newUserStatisticsRequest.gameName}))) AS a \n" +
            "   WHERE userLoginTime >=CONCAT(#{newUserStatisticsRequest.beginTime},' 00:00:00') && userLoginTime<=CONCAT(#{newUserStatisticsRequest.endTime},' 23:59:59') AND userName=a.phone AND userUseDevice=#{newUserStatisticsRequest.userUseDevice} \n" +
            "   GROUP BY userLoginTime\n" +
            ")\n" +
            ") AS a\n" +
            "GROUP BY ddate")
    List<UserNewStatisticsResponse> cudtomDateNewUserStatistics(@Param("newUserStatisticsRequest") NewUserStatisticsRequest newUserStatisticsRequest);

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
            "            WHERE userRegisteredTime<=CONCAT(CURDATE(),' 24:00:00')\n" +
            "            GROUP BY userRegisteredTime\n" +
            "    ) a\n" +
            "GROUP BY ddate")
    List<UserNewStatisticsResponse> allNewUserStatistics();
}
