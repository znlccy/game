package com.youda.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;

import com.youda.model.User;

/**
 * @author Chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @fuction 定义用户映射的接口
 */

@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper {

	/**
	 * 通过用户Id来查询单个用户
	 * @param userId
	 * @return
	 */
	@Select("select * from tb_user where userId=#{userId}")
	public User findByUserId(@Param("userId") long userId);
	
	/**
	 * 通过用户名来查询单个用户
	 * @param userName
	 * @return
	 */
	@Select("select * from tb_user where userName=#{userName}")
	public User findByUserName(@Param("userName") String userName);
	
	/**
	 * 通过用户Id来删除用户
	 * @param userId
	 * @return
	 */
	@Delete("delete from tb_user where userId=#{userId}")
	public boolean deleteByUserId(@Param("userId") long userId);
	
	/**
	 * 通过用户名字来删除用户
	 * @param userName
	 * @return
	 */
	@Delete("delete from tb_user where userName=#{userName}")
	public boolean deleteByUserName(@Param("userName") String userName);
	
	/**
	 * 通过用户Id来修改用户
	 * @param userId
	 * @return
	 */
	@Update("update tb_user set userName=#{user.userName},userPassword=#{user.userPassword},userModifyTime=#{user.userModifyTime} where userId=#{userId} or userName=#{user.UserName}")
	public boolean modifyUserInfo(@Param("user") User user);
	
	/**
	 * 列举所有用户
	 * @return
	 */
	@Select("select * from tb_user")
	public List<User> findAllUser();
	
	/**
	 * 定义用户登录功能的规范
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	@Select("select * from tb_user where userName=#{userName} and userPassword=#{userPassword}")
	public User userLogin(@Param("userName") String userName,@Param("userPassword") String userPassword);

	/**
	 * 定义用户注册功能的规范
	 * @param user
	 * @return
	 */
	@Insert("insert into tb_user(userName,userPassword,userEmail,userRegisteredTime,userUseDevice) values(#{user.userName},#{user.userPassword},#{user.userEmail},#{user.userRegisteredTime},#{user.userUseDevice})")
	public boolean userRegistred(@Param("user") User user);
	
}
