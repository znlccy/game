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
	 * 定义带有参数的游戏构造方法
	 * @param gameId
	 * @param gameName
	 * @param gamePackage
	 */
	public Game(long gameId, String gameName, String gamePackage) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.gamePackage = gamePackage;
	}

	/*定义默认的构造函数*/
	public Game() {
	}
}
