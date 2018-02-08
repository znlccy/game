package com.youda.dao;

import com.youda.model.GameChannel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2017/12/11 9:45
 * @Version 1.0.0
 * @Instructions
 */

@Mapper
public interface GameChannelMapper {

    @Select("select * from tb_gamechannel where gameChannelId = #{id}")
    GameChannel findById(@Param("id") Long gameChannelId);

    @Select("select * from tb_gamechannel where gameId = #{gameId} and channelId = #{channelId}")
    GameChannel findByIds(@Param("gameId") Long gameId, @Param("channelId") Long channelId);

    @Insert("insert into tb_gamechannel(appKey,channelId,gameId) values(#{gameChannel.appKey},#{gameChannel.channelId},#{gameChannel.gameId})")
    @Options(useGeneratedKeys = true, keyProperty = "gameChannel.gameChannelId")
    void add(@Param("gameChannel") GameChannel gameChannel);

    @Select("select * from tb_gamechannel where channelId = #{channelId}")
    @Results({
            @Result(property = "game",
                    column = "gameId",
                    one = @One(select = "com.youda.dao.GameMapper.findByGameId")),
            @Result(property = "googlePayConf",column = "gameChannelId",
                    one = @One(select = "com.youda.dao.ApplePayConfMapper.findByGameChannelId")
            ),
            @Result(property = "applePayConf",column = "gameChannelId",
                    one = @One(select = "com.youda.dao.GooglePayConfMapper.findByGameChannelId")
            )
    })
    List<GameChannel> findByChannel(@Param("channelId") Long channelId);
}
