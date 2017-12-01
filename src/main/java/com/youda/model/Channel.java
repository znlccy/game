package com.youda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义游戏渠道实体类
 */

@Entity
@Table(name = "tb_channel",catalog = "db_ydgame")
public class Channel {
	
	/**
	 * 定义游戏渠道主键Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "channelId")
	private long channelId;
	
	/**
	 * 定义渠道名称
	 */
	@Column(name = "channelNamer")
	private String channelName;
	
	/**
	 * 定义渠道图片
	 */
	@Column(name = "channelPicture")
	private byte[] channelPicture;
	
	/**
	 * 定义渠道网址
	 */
	@Column(name = "channelWebSite")
	private String channelWebSite;
	
	/**
	 * 定义平台性质
	 */
	@Column(name = "platformNature")
	private String platformNature;
	
	/**
	 * 定义平台分类
	 */
	@Column(name = "platformClass")
	private String platformClass;

	/**
	 * 定义渠道标签
	 */
	@Column(name = "channelLanel")
	private String channelLabel;
	
	/**
	 * 定义渠道地区
	 */
	@Column(name = "channelRegion")
	private String channelRegion;

	/**
	 * 定义渠道主键Id的get方法
	 * @return
	 */
	public long getChannelId() {
		return channelId;
	}

	/**
	 * 定义渠道的set方法
	 * @param channelId
	 */
	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	/**
	 * 定义渠道名称的get方法
	 * @return
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * 定义渠道名称的set方法
	 * @param channelName
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * 定义渠道图片的get方法
	 * @return
	 */
	public byte[] getChannelPicture() {
		return channelPicture;
	}

	/**
	 * 定义渠道图片的set方法
	 * @param channelPicture
	 */
	public void setChannelPicture(byte[] channelPicture) {
		this.channelPicture = channelPicture;
	}

	/**
	 * 定义渠道网址的get方法
	 * @return
	 */
	public String getChannelWebSite() {
		return channelWebSite;
	}

	/**
	 * 定义渠道网址的set方法
	 * @param channelWebSite
	 */
	public void setChannelWebSite(String channelWebSite) {
		this.channelWebSite = channelWebSite;
	}

	/**
	 * 定义平台性质的get方法
	 * @return
	 */
	public String getPlatformNature() {
		return platformNature;
	}

	/**
	 * 定义平台性质的set方法
	 * @param platformNature
	 */
	public void setPlatformNature(String platformNature) {
		this.platformNature = platformNature;
	}

	/**
	 * 定义平台分类的get方法
	 * @return
	 */
	public String getPlatformClass() {
		return platformClass;
	}

	/**
	 * 定义平台分类的set方法
	 * @param platformClass
	 */
	public void setPlatformClass(String platformClass) {
		this.platformClass = platformClass;
	}

	/**
	 * 定义渠道标签的get方法
	 * @return
	 */
	public String getChannelLabel() {
		return channelLabel;
	}

	/**
	 * 定义渠道标签的set方法
	 * @param channelLabel
	 */
	public void setChannelLabel(String channelLabel) {
		this.channelLabel = channelLabel;
	}

	/**
	 * 定义渠道地区的get方法
	 * @return
	 */
	public String getChannelRegion() {
		return channelRegion;
	}

	/**H
	 * 定义渠道地区的set方法
	 * @param channelRegion
	 */
	public void setChannelRegion(String channelRegion) {
		this.channelRegion = channelRegion;
	}

	/*定义默认的构造函数*/
	public Channel() {

	}

	public Channel(long channelId, String channelName, byte[] channelPicture, String channelWebSite, String platformNature, String platformClass, String channelLabel, String channelRegion) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelPicture = channelPicture;
		this.channelWebSite = channelWebSite;
		this.platformNature = platformNature;
		this.platformClass = platformClass;
		this.channelLabel = channelLabel;
		this.channelRegion = channelRegion;
	}
}
