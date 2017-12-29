package com.youda.dao.statistics;

import com.youda.model.statistics.UserCaculator;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/29 17:00
 * @Version 1.0.0
 * @Instructions 实现用户统计
 */

@Mapper
public interface UserCaculatorMapper {

    /*添加用户统计记录*/
    @Insert("insert into tb_user_caculator(gameChannelId,userId,userLoginTime,userRegistedTime,userUseDevice) values(#{userCaculator.gameChannelId},#{userCaculator.userId},#{userCaculator.userLoginTime},#{userCaculator.userRegistedTime},#{userCaculator.userUseDevice})")
    @Options(useGeneratedKeys = true,keyProperty = "userCaculator.userCaculatorId")
    boolean addUserCaculator(@Param("userCaculator") UserCaculator userCaculator);

    /*根据游戏渠道主键删除用户统计*/
    @Delete("delete from tb_user_caculator where gameChannelId=#{gameChannelId}")
    boolean deleteByGameChannelId(@Param("gameChannelId") Long gameChannelId);

    /*根据用户主键来删除用户统计*/
    @Delete("delete from tb_user_caculator where userId=#{userId}")
    boolean deleteByUserId(@Param("userId") Long userId);

    /*根据用户统计主键来删除用户统计*/
    @Delete("delete from tb_user_caculator where userCaculatorId=#{userCaculatorId}")
    boolean deleteByCacultorId(@Param("userCaculatorId") Long userCaculatorId);

    /*更新用户统计信息*/
    @Update("update tb_user_caculator set gameChannelId=#{userCaculator.gameChannelId},userId=#{userCaculator.userId},userLoginTime=#{userCaculator.userLoginTime},userRegistedTime=#{userCaculator.userRegistedTime},userUseDevice=#{userCaculator.userUseDevice}")
    boolean modifyUserCaculator(@Param("userCaculator") UserCaculator userCaculator);

    /*通过用户统计主键来查询用户统计*/
    @Select("select * from tb_user_caculator where userCaculatorId=#{userCaculatorId}")
    UserCaculator findByCaculatorId(@Param("userCaculatorId") Long userCaculatorId);

    @Select("select * from tb_user_caculator where gameChannelId=#{gameChannelId}")
    List<UserCaculator> findByGameChannelId(@Param("gameChannelId") Long gameChannelId);

    /*通过用户主键来查找用户统计*/
    @Select("select * from tb_user_caculator where userId=#{userId}")
    List<UserCaculator> findByUserId(@Param("userId") Long userId);

    /*通过游戏渠道和用户主键来查找用户统计*/
    @Select("select * from tb_user_caculator where userId=#{userId} and gameChannelId=#{gameChannelId}")
    List<UserCaculator> findByGameChannelIdAndUserId(@Param("gameChannelId") Long gameChannelId,@Param("userId") Long userId);

}
