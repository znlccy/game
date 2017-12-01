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
	@Insert("insert into tb_game(game_id,game_name,game_package) values(#{game.gameId},#{game.gameName},#{game.gamePackage})")
	public boolean addGame(@Param("game") Game game);
	
	/**
	 * 定义通过游戏主键Id来查询游戏的规范
	 * @param gameId
	 * @return
	 */
	@Select("select game_id,game_name,game_package from tb_game where game_id=#{gameId}")
	public Game findByGameId(@Param("gameId") long gameId);
	
	/**
	 * 定义通过游戏名称来查询游戏的规范
	 * @param gameName
	 * @return
	 */
	@Select("select game_id,game_name,game_package from tb_game where game_name=#{gameName}")
	public Game findByGameName(@Param("gameName") String gameName);
	
	/**
	 * 定义通过游戏包名来查询游戏的规范
	 * @param gamePackage
	 * @return
	 */
	@Select("select game_id,game_name,game_package from tb_game where game_package=#{gamePackage}")
	public Game findByGamePackage(@Param("gamePackage") String gamePackage);
	
	/**
	 * 定义通过游戏主键Id来更改游戏的规范
	 * @param gameName
	 * @param gamePackage
	 * @param gameId
	 * @return
	 */
	@Update("update tb_game set game_name=#{game.gameName},game_package=#{game.gamePackage} where game_id=#{gameId}")
	public boolean modifyByGameId(@Param("gameId") long gameId);
	
	/**
	 * 定义通过游戏名称来更改游戏的规范
	 * @param gameName
	 * @param gamePackage
	 * @return
	 */
	@Update("update tb_game set gamePackage=#{gamePackage},gameName=#{gameName} where gameName=#{gameName}")
	public boolean modifyByGameName(@Param("gameName") String gameName,@Param("gamePackage") String gamePackage);
	
	/**
	 * 定义通过游戏包名来更改游戏的规范
	 * @param gamePackage
	 * @param gameName
	 * @return
	 */
	@Update("update tb_game set gamePackage=#{gamePackage},gameName=#{gameName} where gamePackage=#{gamePackage}")
	public boolean modifyByGamePackage(@Param("gamePackage") String gamePackage,@Param("gameName") String gameName);
	
	/**
	 * 定义通过游戏主键Id来删除游戏的规范
	 * @param gameId
	 * @return
	 */
	@Delete("delete from tb_game where gameId=#{gameId}")
	public boolean deleteByGameId(@Param("gameId") String gameId);
	
	/**
	 * 定义通过游戏名称来删除游戏
	 * @param gameName
	 * @return
	 */
	@Delete("delete from tb_game where gameName=#{gameName}")
	public boolean deleteByGameName(@Param("gameName") String gameName);
	
	/**
	 * 定义通过游戏包名来删除游戏的规范
	 * @param gamePackage
	 * @return
	 */
	@Delete("delete from tb_game where gamePackage=#{gamePackage}")
	public boolean deleteByGamePackage(@Param("gamePackage") String gamePackage);
	
	/**
	 * 定义查询所有游戏的规范
	 * @return
	 */
	@Select("select gameId,gameName,gamePackage from tb_game")
	public List<Game> findAllGame();
}
