package com.youda.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;

import com.youda.model.User;

/**
 * @author Chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @fuction 定义用户映射的接口
 */

@Mapper
public interface UserMapper {

	/**
	 * 通过用户Id来查询单个用户
	 * @param userId
	 * @return
	 */
	@Select("select user_id,user_name,user_password,user_login_status,user_login_time,user_modify_time,user_registered_time,user_logout_time,user_login_type,user_online_time,user_use_device from tb_user where user_id=#{user_id}")
	public User findByUserId(@Param("user_id") String userId);
	
	/**
	 * 通过用户名来查询单个用户
	 * @param userName
	 * @return
	 */
	@Select("select userId,userName,userPassword,userLoginStatus,userLoginTime,userModifyTime,userRegisteredTime,userLogoutTime,userLoginType,userOnlineTime,userUseDevice from tb_user where userName=#{userName}")
	public User findByUserName(@Param("userName") String userName);
	
	/**
	 * 通过用户Id来删除用户
	 * @param userId
	 * @return
	 */
	@Delete("delete from tb_user where userId=#{userId}")
	public boolean deleteByUserId(@Param("userId") String userId);
	
	/**
	 * 通过用户名字来删除用户
	 * @param userName
	 * @return
	 */
	@Delete("delete from tb_user where userId=#{userName}")
	public boolean deleteByUserName(@Param("userName") String userName);
	
	/**
	 * 通过用户Id来修改用户
	 * @param userId
	 * @return
	 */
	@Update("update tb_user set userName=#{user.userName} where userId=#{user.userId}")
	public boolean updateByUserId(@Param("user") User user);
	
	/**
	 * 定义通过用户名来修改用户
	 * @param userName
	 * @return
	 */
	@Update("update tb_user set userName=#{user.userName},userPassword=#{user.userPassword},userLoginStatus=#{user.userLoginStatus},userLoginTime=#{user.userLoginTime},userModifyTime=#{user.userModifyTime},userRegisteredTime=#{user.userRegisteredTime},userLogoutTime=#{user.userLogoutTime},userLoginType=#{user.userLoginType},userOnlineTime=#{user.userOnlineTime},userUseDevice=#{user.userUseDevice} where userName=#{user.userName}")
	public boolean updateByUserName(@Param("user") User user);
	
	/**
	 * 列举所有用户
	 * @return
	 */
	@Select("select userId,userName,userPassword,userLoginStatus,userLoginTime,userModifyTime,userRegisteredTime,userLogoutTime,userLoginType,userOnlineTime,userUseDevice from tb_user")
	public List<User> findAllUser();
	
	/**
	 * 定义用户登录功能的规范
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	@Select("")
	public User login(@Param("userName") String userName,@Param("userPassword") String userPassword);
	
}
