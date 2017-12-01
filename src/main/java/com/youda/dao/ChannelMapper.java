package com.youda.dao;

import java.util.List;

import javax.persistence.ExcludeSuperclassListeners;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youda.model.Channel;

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
	 * @param channel
	 * @return
	 */
	@Insert("insert into tb_channel(channel_id,channel_name,channel_web_site,platform_nature,platform_class,channel_label,channel_region) values(#{channel.channelId},#{channel.channelName},#{channel.channelWebSite},#{channel.platformNature},#{channel.platformClass},#{channel.channelLabel},#{channel.channelRegion})")
	public boolean addChannel(@Param("channel") Channel channel);
	
	/**
	 * 定义通过游戏渠道主键Id来查找渠道的规范
	 * @param channelId
	 * @return
	 */
	@Select("select channel_id,channel_name,channel_web_site,platform_nature,platform_class,channel_label,channel_region from tb_channel where channel_id=#{channelId}")
	public Channel findByChannelId(@Param("channelId") long channelId);
	
	/**
	 * 定义通过游戏渠道名称来查找渠道的规范
	 * @param channelName
	 * @return
	 */
	@Select("select channel_id,channel_name,channel_web_site,platform_nature,platform_class,channel_label,channel_region from tb_channel where channel_name=#{channelName}")
	public Channel findByChannelName(@Param("channalName") String channelName);
	
	/**
	 * 定义通过游戏渠道主键Id来删除渠道的规范
	 * @param channelId
	 * @return
	 */
	@Delete("delete from tb_channel where channel_id=#{channelId}")
	public boolean deleteByChannelId(@Param("channelId") long channelId);
	
	/**
	 * 定义通过游戏渠道名称来删除渠道的规范
	 * @param channelName
	 * @return
	 */
	@Delete("delete from tb_channel where channel_name=#{channelName}")
	public boolean deleteByChannelName(@Param("channelName") String channelName);

	/**
	 * 定义通过游戏渠道主键Id来修改渠道的规范
	 * @param channelId
	 * @param channel
	 * @return
	 */
	@Update("update tb_channel set channel_name=#{channel.channelName},channel_web_site=#{channel.channelWebSite},channel_nature=#{channel.channelNature},platform_nature=#{channel.platformNature},platform_class=#{channel.platformClass},channel_label=#{channel.channalLabel},channel_region=#{channel.channelRegion} where channel_id=#{channel.channelId}")
	public boolean modifyByChannelId(@Param("channel") Channel channel);
	
	/**
	 * 定义通过游戏渠道名称来修改渠道的规范
	 * @param channelName
	 * @param channel
	 * @return
	 */
	@Update("update tb_channel set channel_name=#{channel.channelName},channel_web_site=#{channel.channelWebSite},channel_nature=#{channel.channelNature},platform_nature=#{channel.platformNature},platform_class=#{channel.platformClass},channel_label=#{channel.channalLabel},channel_region=#{channel.channelRegion} where channel_name=#{channel.channelName}")
	public boolean modifyByChannelName(@Param("channel") Channel channel);
	
	/**
	 * 定义列举所有游戏渠道的规范
	 * @return
	 */
	@Select("select channel_id,channel_name,channel_web_site,platform_nature,platform_class,channel_label,channel_region from tb_channel")
	public List<Channel> findAllChannel();
}
