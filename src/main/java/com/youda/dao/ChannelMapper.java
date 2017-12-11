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
	@Insert("insert into tb_channel(channelId,channelName,channelWebSite,platformNature,platformClass,channelLabel,channelRegion) values(#{channel.channelId},#{channel.channelName},#{channel.channelWebSite},#{channel.platformNature},#{channel.platformClass},#{channel.channelLabel},#{channel.channelRegion})")
	public boolean addChannel(@Param("channel") Channel channel);
	
	/**
	 * 定义通过游戏渠道主键Id来查找渠道的规范
	 * @param channelId
	 * @return
	 */
	@Select("select channelId,channelName,channelWebSite,platformNature,platformClass,channelLabel,channelRegion from tb_channel where channelId=#{channelId}")
	public Channel findByChannelId(@Param("channelId") long channelId);
	
	/**
	 * 定义通过游戏渠道名称来查找渠道的规范
	 * @param channelName
	 * @return
	 */
	@Select("select channelId,channelName,channelWebSite,platformNature,platformClass,channelLabel,channelRegion from tb_channel where channelName=#{channelName}")
	public Channel findByChannelName(@Param("channalName") String channelName);
	
	/**
	 * 定义通过游戏渠道主键Id来删除渠道的规范
	 * @param channelId
	 * @return
	 */
	@Delete("delete from tb_channel where channelId=#{channelId}")
	public boolean deleteByChannelId(@Param("channelId") long channelId);
	
	/**
	 * 定义通过游戏渠道名称来删除渠道的规范
	 * @param channelName
	 * @return
	 */
	@Delete("delete from tb_channel where channelName=#{channelName}")
	public boolean deleteByChannelName(@Param("channelName") String channelName);

	/**
	 * 定义通过游戏渠道主键Id来修改渠道的规范
	 * @param channelId
	 * @return
	 */
	@Update("update tb_channel set channelName=#{channel.channelName},channelWebSite=#{channel.channelWebSite},channelNature=#{channel.channelNature},platformNature=#{channel.platformNature},platformClass=#{channel.platformClass},channelLabel=#{channel.channalLabel},channelRegion=#{channel.channelRegion} where channelId=#{channel.channelId}")
	public boolean modifyByChannelId(@Param("channelId") long channelId);

	/**
	 * 定义通过游戏渠道名称来修改渠道的规范
	 * @param channel
	 * @return
	 */
	@Update("update tb_channel set channelName=#{channel.channelName},channelWebSite=#{channel.channelWebSite},channelNature=#{channel.channelNature},platformNature=#{channel.platformNature},platformClass=#{channel.platformClass},channeLabel=#{channel.channalLabel},channelRegion=#{channel.channelRegion} where channelName=#{channel.channelName}")
	public boolean modifyByChannelName(@Param("channel") Channel channel);

	/**
	 * 定义列举所有游戏渠道的规范
	 * @return
	 */
	@Select("select * from tb_channel")
	public List<Channel> findAllChannel();
}
