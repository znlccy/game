package com.youda.dao;

import com.youda.model.Channel;
import com.youda.model.ChannelUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义游戏渠道映射接口
 */

@Mapper
public interface ChannelMapper {

    /**
     * 定义游戏渠道的规范
     *
     * @param channel
     * @return
     */
    @Insert("insert into tb_channel(channelName,channelWebSite,platformNature,platformClass,channelLabel,channelRegion) values(#{channel.channelName},#{channel.channelWebSite},#{channel.platformNature},#{channel.platformClass},#{channel.channelLabel},#{channel.channelRegion})")
    @Options(useGeneratedKeys = true, keyProperty = "channel.channelId")
    void addChannel(@Param("channel") Channel channel);

    /**
     * 定义通过游戏渠道主键Id来查找渠道的规范
     *
     * @param channelId
     * @return
     */
    @Select("select channelId,channelName,channelWebSite,platformNature,platformClass,channelLabel,channelRegion from tb_channel where channelId=#{channelId}")
    Channel findByChannelId(@Param("channelId") long channelId);

    /**
     * 定义通过游戏渠道名称来查找渠道的规范
     *
     * @param channelName
     * @return
     */
    @Select("select channelId,channelName,channelWebSite,platformNature,platformClass,channelLabel,channelRegion from tb_channel where channelName=#{channelName}")
    Channel findByChannelName(@Param("channalName") String channelName);

    /**
     * 定义通过游戏渠道主键Id来删除渠道的规范
     *
     * @param channelId
     * @return
     */
    @Delete("delete from tb_channel where channelId=#{channelId}")
    boolean deleteByChannelId(@Param("channelId") long channelId);

    /**
     * 定义通过游戏渠道名称来删除渠道的规范
     *
     * @param channelName
     * @return
     */
    @Delete("delete from tb_channel where channelName=#{channelName}")
    boolean deleteByChannelName(@Param("channelName") String channelName);

    /**
     * 定义通过游戏渠道主键Id来修改渠道的规范
     *
     * @param channelId
     * @return
     */
    @Update("update tb_channel set channelName=#{channel.channelName},channelWebSite=#{channel.channelWebSite},channelNature=#{channel.channelNature},platformNature=#{channel.platformNature},platformClass=#{channel.platformClass},channelLabel=#{channel.channalLabel},channelRegion=#{channel.channelRegion} where channelId=#{channel.channelId}")
    boolean modifyByChannelId(@Param("channelId") long channelId);

    /**
     * 定义通过游戏渠道名称来修改渠道的规范
     *
     * @param channel
     * @return
     */
    @Update("update tb_channel set channelName=#{channel.channelName},channelWebSite=#{channel.channelWebSite},channelNature=#{channel.channelNature},platformNature=#{channel.platformNature},platformClass=#{channel.platformClass},channeLabel=#{channel.channalLabel},channelRegion=#{channel.channelRegion} where channelName=#{channel.channelName}")
    boolean modifyByChannelName(@Param("channel") Channel channel);

    /**
     * 定义列举所有游戏渠道的规范
     *
     * @return
     */
    @Select("select * from tb_channel")
    List<Channel> findAllChannel();

    @Insert("insert into tb_channel_user(phone,password,createTime,channelId,token) " +
            "values (#{user.phone},#{user.password},#{user.createTime},#{user.channelId},#{user.token})")
    @Options(useGeneratedKeys = true, keyProperty = "user.channelUserId")
    void addChannelUser(@Param("user") ChannelUser user);

    @Select("select * from tb_channel_user where phone = #{phone}")
    ChannelUser findAllChannelUser(@Param("phone") String phone);

    @Select("select * from tb_channel_user where channelId = #{channelId}")
    ChannelUser findChannelById(@Param("channelId") Long id);

    @Update("update tb_channel_user set token = #{user.token} where channelUserId = #{user.channelUserId}")
    void updateToken(@Param("user") ChannelUser user);
}
