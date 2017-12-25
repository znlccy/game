package com.youda.dao;

import com.youda.model.Token;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义token映射接口
 */

@Mapper
public interface TokenMapper {

    /**
     * 定义添加token的规范
     *
     * @param token
     * @return
     */
    @Insert("replace into tb_token(accessToken,expirationTime,userId,gameChannelId) values(#{token.accessToken},#{token.expirationTime},#{token.userId},#{token.gameChannelId})")
    @Options(useGeneratedKeys = true, keyProperty = "tokenId", keyColumn = "tokenId")
    boolean addToken(@Param("token") Token token);


    /**
     * 定义通过tokenId来查询token的规范
     *
     * @param tokenId
     * @return
     */
    @Select("select tokenId,accessToken,expirationTime from tb_token where tokenId=#{tokenId}")
    Token findByTokenId(@Param("tokenId") long tokenId);

    /**
     * 定义通过token值来查询token的规范
     *
     * @return
     */
    @Select("select tokenId,accessToken,expirationTime from tb_token where token=#{accessToken}")
    Token findByToken(@Param("accessToken") String accessToken);

    /**
     * 定义通过token主键Id来更改token的值的规范
     *
     * @param token
     * @return
     */
    @Update("update tb_token set accessToken=#{token.accessToken},expirationTime=#{token.expirationTime} where tokenId=#{token.tokenId}")
    boolean modifyByTokenId(@Param("token") Token token);

    /**
     * 定义通过token来更改token的值
     *
     * @param token
     * @return
     */
    @Update("update tb_token set accessToken=#{token.accessToken},expirationTime=#{token.expirationTime} where accessToken=#{token.accessToken}")
    boolean modifyByAccessToken(@Param("token") Token token);

    /**
     * 定义通过tokenId主键来删除token的规范
     *
     * @param tokenId
     * @return
     */
    @Delete("delete from tb_token where tokenId=#{#tokenId}")
    boolean deleteByTokenId(@Param("tokenId") String tokenId);

    /**
     * 定义通过tokenId主键来删除token的规范
     *
     * @param userId
     * @return
     */
    @Delete("delete from tb_token where userId=#{userId}")
    boolean deleteByUserId(@Param("userId") Long userId);


    /**
     * 定义通过token来删除token的规范
     *
     * @param accessToken
     * @return
     */
    @Delete("delete from tb_token where accessToken=#{accessToken}")
    boolean deleteByToken(@Param("accessToken") String accessToken);

    /**
     * 定义列举所有token的规范
     *
     * @return
     */
    @Select("select * from tb_token")
    List<Token> findAllToken();


    /**
     * 通过用户ID查询token
     *
     * @param userId channelGameId
     * @return Token
     */
    @Select("select * from tb_token where userId = #{userId} and gameChannelId = #{gameChannelId} ")
    Token findTokenByIds(@Param("userId") Long userId, @Param("gameChannelId") Long gameChannelId);
}
