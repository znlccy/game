package com.youda.dao.statistics;

import com.youda.request.statistics.StatisticsRequest;
import com.youda.response.statistics.UserRetainedResponse;
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
public interface UserRetainedMapper {

    /*实现自定义日期用户留存统计*/
    @Select("SELECT (today.todayUserRetained/customrate.customUserRetained)*100 AS userRetainedCount,#{endTime} AS ddate FROM \n" +
            "(SELECT COUNT(*) AS customUserRetained,CURDATE() AS ddate FROM tb_user\n" +
            "WHERE userLoginTime>=CONCAT(#{beginTime},' 00:00:00') AND userLoginTime<=CONCAT(#{endTime},' 24:00:00')) AS customrate,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today")
    List<UserRetainedResponse> customTime(@Param("statisticsRequest") StatisticsRequest statisticsRequest);

    /*实现查询所有用户留存的统计*/
    @Select("SELECT (today.todayUserRetained/allrate.allUserRetained)*100 AS userRetainedCount,CURDATE() AS ddate FROM \n" +
            "(SELECT COUNT(*) AS allUserRetained, CURDATE() AS ddate FROM tb_user WHERE userLoginTime <> NULL) AS allrate,\n" +
            " (SELECT COUNT(*) AS todayUserRetained,CURDATE() AS ddate FROM tb_user WHERE userLoginTime >=CONCAT(CURDATE(),' 00:00:00') \n" +
            "AND userLoginTime<=CONCAT(CURDATE(),' 24:00:00')) AS today\n")
    List<UserRetainedResponse> all(@Param("statisticsRequest") StatisticsRequest statisticsRequest);
}
