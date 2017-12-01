package com.youda.util;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-29
 * @introduce 实现签名数据
 */

public class SignatureData {
	
	/**
	 * 声明加密的用户名
	 */
	private String userName;
	
	/**
	 * 声明加密的游戏名
	 */
	private String gameName;
	
	/**
	 * 声明加密的渠道名
	 */
	private String channelName;
	
	/**
	 * 声明加密的渠道主键Id
	 */
	private String channelId;
	
	/**
	 * 声明加密的用户主键Id
	 */
	private String userId;
	
	/**
	 * 声明加密的游戏Id
	 */
	private String gameId;
	
	/**
	 * 声明加密返回给客户端的appKey
	 */
	private String appKey;

	/**
	 * 实现获取用户名的get方法
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 实现获取用户名的set方法
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 实现获取游戏名的get方法
	 * @return
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * 实现获取游戏名的set方法
	 * @param gameName
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * 实现获取游戏渠道的get方法
	 * @return
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * 实现获取游戏渠道的set方法
	 * @param channelName
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * 实现获取游戏渠道主键Id的get方法
	 * @return
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * 实现获取游戏渠道主键Id的set方法
	 * @param channelId
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * 实现获取用户主键Id的get方法
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 实现获取用户主键Id的set方法
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 实现获取游戏主键Id的get方法
	 * @return
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * 实现获取游戏主键Id的set方法
	 * @param gameId
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * 实现返回给客户端的appkey的get方法
	 * @return
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * 实现返回给客户端的appkey的set方法
	 * @param appKey
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * 实现带有参数的构造函数
	 * @param userName
	 * @param gameName
	 * @param channelName
	 * @param channelId
	 * @param userId
	 * @param gameId
	 * @param appKey
	 */
	public SignatureData(String userName, String gameName, String channelName, String channelId, String userId,
			String gameId, String appKey) {
		super();
		this.userName = userName;
		this.gameName = gameName;
		this.channelName = channelName;
		this.channelId = channelId;
		this.userId = userId;
		this.gameId = gameId;
		this.appKey = appKey;
	}
	
	
	
}
