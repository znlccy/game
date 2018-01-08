package com.youda.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义游戏实体类
 */

@Entity
@Table(name = "tb_game", catalog = "db_ydgame")
public class Game {
	
	/**
	 * 定义游戏的主键Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gameId")
	private long gameId;
	
	/**
	 * 定义游戏的名称
	 */
	@Column(name = "gameName")
	private String gameName;
	
	/**
	 * 定义游戏的包名
	 */
	@Column(name = "gamePackage")
	private String gamePackage;

	/**
	 * 由游戏去维护多对多关系
	 */
	@ManyToMany
	@MapKey(name = "gameChannelId")
	@JoinTable(name = "tb_gamechannel",joinColumns = @JoinColumn(name = "gameId"),inverseJoinColumns = @JoinColumn(name = "channelId"))
	private Set<Channel> channels = new HashSet<Channel>();

/*
	*/
/**
	 * 定义游戏和订单之间的一对多关系映射
	 *//*

	@OneToMany(cascade= {CascadeType.ALL},mappedBy="game")
	private Set<Order> orders = new HashSet<Order>();
*/

	/**
	 * 实现游戏主键Id的get方法
	 * @return
	 */
	public long getGameId() {
		return gameId;
	}

	/**
	 * 实现游戏主键Id的set方法
	 * @param gameId
	 */
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	/**
	 * 实现游戏名称的get方法
	 * @return
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * 实现游戏名称的set方法
	 * @param gameName
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	/**
	 * 实现游戏包名的get方法
	 * @return
	 */
	public String getGamePackage() {
		return gamePackage;
	}

	/**
	 * 实现游戏名称的set方法
	 * @param gamePackage
	 */
	public void setGamePackage(String gamePackage) {
		this.gamePackage = gamePackage;
	}
	
	/**
	 * 实现获取渠道的get方法
	 * @return
	 */
	public Set<Channel> getChannels() {
		return channels;
	}

	/**
	 * 实现获取渠道的set方法
	 * @param channels
	 */
	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
	
	/**
	 * 实现游戏和订单之间一对多关系的get方法
	 * @return
	 */
	/*public Set<Order> getOrders() {
		return orders;
	}

	*//**
	 * 实现游戏和订单之间一对多关系的set方法
	 * @param
	 *//*
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
*/
	/*定义默认的构造函数*/
	public Game() {
		
	}
	
}
