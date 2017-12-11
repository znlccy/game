
package com.youda.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youda.dao.GameMapper;
import com.youda.model.Game;
import com.youda.service.GameService;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现游戏服务接口
 */

@Service
public class GameServiceImpl implements GameService {

	/**
	 * 实现游戏映射接口的自动依赖注入
	 */
	@Autowired
	private GameMapper gameMapper;

	/*添加游戏*/
	@Override
	public boolean addGame(Game game) {
		return gameMapper.addGame(game);
	}

	/*通过游戏主键删除游戏*/
	@Override
	public boolean deleteByGameId(long gameId) {
		return gameMapper.deleteByGameId(gameId);
	}

	/*通过游戏名称来删除游戏*/
	@Override
	public boolean deleteByGameName(String gameName) {
		return gameMapper.deleteByGameName(gameName);
	}

	/*通过游戏主键Id来修改游戏*/
	@Override
	public boolean modifyByGameId(long gameId) {
		return gameMapper.modifyByGameId(gameId);
	}

	/*通过游戏名称来修改游戏*/
	@Override
	public boolean modifyByGameName(String gameName) {
		return gameMapper.modifyByGameName(gameName);
	}

	/*通过游戏主键Id来查找游戏*/
	@Override
	public Game findByGameId(long gameId) {
		return gameMapper.findByGameId(gameId);
	}

	/*通过游戏名称来查找游戏*/
	@Override
	public Game findByGameName(String gameName) {
		return gameMapper.findByGameName(gameName);
	}

	/*查询所有的游戏*/
	@Override
	public List<Game> findAllGames() {
		return gameMapper.findAllGame();
	}
}
