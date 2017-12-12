package com.youda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Chencongye
 * @Date 2017/12/11 9:42
 * @Version 1.0.0
 * @Instructions 定义用户游戏Dao层接口
 */

@Mapper
public interface UserGameMapper {

    @Insert("insert into tb_usergame(userGameId,userId,gameId) values(121,#{userId},#{gameId})")
    @Options(useGeneratedKeys = true,keyProperty = "userGameId")
    void userLoginGame(@Param("userId") long userId,@Param("gameId") long gameId);
}
