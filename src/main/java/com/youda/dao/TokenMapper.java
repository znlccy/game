package com.youda.dao;

import com.youda.model.Token;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义token映射接口
 */

@Mapper
public interface TokenMapper {
	
	/**
	 * 定义添加token的规范
	 * @param token
	 * @return
	 */
	@Insert("insert into tb_token(tokenId,token,createTokenTime,createTokenUser) values(#{token.tokenId},#{token.token},#{token.createTokenTime},#{token.createTokenUser})")
	public boolean addToken(@Param("token") Token token);
	
	/**
	 * 定义通过tokenId来查询token的规范
	 * @param tokenId
	 * @return
	 */
	@Select("select tokenId,token,createTokenTime,createTokenUser from tb_token where tokenId=#{tokenId}")
	public Token findByTokenId(@Param("tokenId") String tokenId);
	
	/**
	 * 定义通过token值来查询token的规范
	 * @param token
	 * @return
	 */
	@Select("select tokenId,token,createTokenTime,createTokenUser from tb_token where token=#{token}")
	public Token findByToken(@Param("token") String token);
	
	/**
	 * 定义通过token主键Id来更改token的值的规范
	 * @param tokenId
	 * @param token
	 * @param createTokenTime
	 * @param createTokenUser
	 * @return
	 */
	@Update("update tb_token set token=#{token},createTokenTime=#{createTokenTime},createTokenUser=#{createTokenUser} where tokenId=#{tokenId}")
	public boolean modifyByTokenId(@Param("tokenId") String tokenId,@Param("token") String token,@Param("createTokenTime") Timestamp createTokenTime,@Param("createTokenUser") String createTokenUser);
	
	/**
	 * 定义通过token来更改token的值
	 * @param token
	 * @param createTokenTime
	 * @param createTokenUser
	 * @return
	 */
	@Update("update tb_token set token=#{token},createTokenTime=#{createTokenTime},createTokenUser=#{createTokenUser} where token=#{token}")
	public boolean modifyByToken(@Param("token") String token,@Param("createTokenTime") Timestamp createTokenTime,@Param("createTokenUser") String createTokenUser);
	
	/**
	 * 定义通过tokenId主键来删除token的规范
	 * @param tokenId
	 * @return
	 */
	@Delete("delete from tb_token where tokenId=#{#tokenId}")
	public boolean deleteByTokenId(@Param("tokenId") String tokenId);
	
	/**
	 * 定义通过token来删除token的规范
	 * @param token
	 * @return
	 */
	@Delete("delete from tb_token where token=#{token}")
	public boolean deleteByToken(@Param("token") String token);
	
	/**
	 * 定义列举所有token的规范
	 * @return
	 */
	@Select("select tokenId,token,createTokenTime,createTokenUser from tb_token")
	public List<Token> findAllToken();

	/*@Select("select")*/
	Token findTokenByUserId(String userId);
	
}
