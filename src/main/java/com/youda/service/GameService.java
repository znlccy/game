package com.youda.service;

import com.youda.model.Game;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义游戏接口的规范
 */

public interface GameService {
	
	/**
	 * 定义创建游戏的方法
	 * @return false
	 */
	boolean addGame(Game game);

	/**
	 * 定义通过游戏Id删除游戏的方法
	 * @param gameId
	 * @return false
	 */
	boolean deleteByGameId(long gameId);

	/**
	 * 定义通过游戏名删除游戏的方法
	 * @param gameName
	 * @return false
	 */
	boolean deleteByGameName(String gameName);

	/**
	 * 定义通过游戏Id修改游戏的方法
	 * @param gameId
	 * @return false
	 */
	boolean modifyByGameId(long gameId);

	/**
	 * 定义创建游戏的方法
	 * @param gameName
	 * @return false
	 */
	boolean modifyByGameName(String gameName);

	/**
	 * 定义创建游戏的方法
	 * @param gameId
	 * @return game
	 */
	Game findByGameId(long gameId);

	/**
	 * 定义创建游戏的方法
	 * @param gameName
	 * @return game
	 */
	Game findByGameName(String gameName);

	/**
	 * 定义创建游戏的方法
	 * return
	 */
	List<Game> findAllGames();
}
