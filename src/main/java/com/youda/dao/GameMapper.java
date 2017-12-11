package com.youda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youda.model.Game;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义游戏映射接口
 */

@Mapper
public interface GameMapper {
	
	/**
	 * 定义创建游戏的规范
	 * @return
	 */
	@Insert("insert into tb_game(gameId,gameName,gamePackage) values(#{game.gameId},#{game.gameName},#{game.gamePackage})")
	boolean addGame(@Param("game") Game game);
	
	/**
	 * 定义通过游戏主键Id来查询游戏的规范
	 * @param gameId
	 * @return
	 */
	@Select("select gameId,gameName,gamePackage from tb_game where gameId=#{gameId}")
	Game findByGameId(@Param("gameId") long gameId);
	
	/**
	 * 定义通过游戏名称来查询游戏的规范
	 * @param gameName
	 * @return
	 */
	@Select("select gameId,gameName,gamePackage from tb_game where gameName=#{gameName}")
	Game findByGameName(@Param("gameName") String gameName);
	
	/**
	 * 定义通过游戏包名来查询游戏的规范
	 * @param gamePackage
	 * @return
	 */
	@Select("select gameId,gameName,gamePackage from tb_game where gamePackage=#{gamePackage}")
	Game findByGamePackage(@Param("gamePackage") String gamePackage);
	
	/**
	 * 定义通过游戏主键Id来更改游戏的规范
	 * @param gameId
	 * @return
	 */
	@Update("update tb_game set gameName=#{game.gameName},gamePackage=#{game.gamePackage} where gameId=#{gameId}")
	boolean modifyByGameId(@Param("gameId") long gameId);
	
	/**
	 * 定义通过游戏名称来更改游戏的规范
	 * @param gameName
	 * @return
	 */
	@Update("update tb_game set gamePackage=#{game.gamePackage},game_name=#{gameName} where game_name=#{gameName}")
	public boolean modifyByGameName(@Param("gameName") String gameName);
	
	/**
	 * 定义通过游戏包名来更改游戏的规范
	 * @param gamePackage
	 * @return
	 */
	@Update("update tb_game set gamePackage=#{gamePackage},gameName=#{gameName} where gamePackage=#{gamePackage}")
	boolean modifyByGamePackage(@Param("gamePackage") String gamePackage);
	
	/**
	 * 定义通过游戏主键Id来删除游戏的规范
	 * @param gameId
	 * @return
	 */
	@Delete("delete from tb_game where gameId=#{gameId}")
	public boolean deleteByGameId(@Param("gameId") long gameId);
	
	/**
	 * 定义通过游戏名称来删除游戏
	 * @param gameName
	 * @return
	 */
	@Delete("delete from tb_game where gameName=#{gameName}")
	boolean deleteByGameName(@Param("gameName") String gameName);
	
	/**
	 * 定义通过游戏包名来删除游戏的规范
	 * @param gamePackage
	 * @return
	 */
	@Delete("delete from tb_game where gamePackage=#{gamePackage}")
	boolean deleteByGamePackage(@Param("gamePackage") String gamePackage);
	
	/**
	 * 定义查询所有游戏的规范
	 * @return
	 */
	@Select("select gameId,gameName,gamePackage from tb_game")
	List<Game> findAllGame();
}
