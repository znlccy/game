package com.youda.dao.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserStatisticsMapper {

    /*实现新增用户或者账户的统计*/
    @Select("select count(*) as newUser,userRegisteredTime as time from tb_user group by DATE_FORMAT(userRegisteredTime,'%Y')")
    void newUserStatistics();

    /*实现新增设备的统计*/
    @Select("select count(*) as newEquipment from tb_user group by date_format(userRegisteredTime,'%Y')")
    void newEquipmentStatistics();

    /*实现每日新增用户的统计*/
    @Select("select count(*) from tb_user group by date_format(now(),'%D')")
    void everyDayNewUserStatistics();

    /*实现每周新增用户的统计*/
    @Select("select * from tb_user")
    void everyWeekNewUserStatistics();

    /*实现每月新增用户的统计*/
    @Select("select * from tb_user")
    void everyMonthNewUserStatistics();

    /*实现当日活跃用户统计*/
    @Select("select * from tb_user")
    void dengesActiveStatistics();

    /*实现当日付费用户统计*/
    @Select("select * from tb_user")
    void dengesPaymentStatistics();

    /*实现当日收入的统计*/
    @Select("select * from tb_user")
    void dengesIncomeStatistics();

    /*实现用户留存统计*/
    @Select("select * from tb_user")
    void newUsersRetained();

}
