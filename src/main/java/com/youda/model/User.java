package com.youda.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义用户实体类
 */

@Entity
@Table(name = "tb_user", catalog = "db_ydgame")
public class User {
	
	/**
	 * 定义用户主键Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId")
	private long userId;
	
	/**
	 * 定义用户名称
	 */
	@Column(name = "userName")
	private String userName;
	
	/**
	 * 定义用户密码 
	 */
	@Column(name = "userPassword")
	private String userPassword;
	
	/**
	 * 定义用户登录状态
	 */
	@Column(name = "userLoginStatus")
	private String userLoginStatus;
	
	/**
	 * 定义用户登录时间
	 */
	@Column(name = "userLoginTime")
	private Timestamp userLoginTime;
	
	/**
	 * 定义用户注册时间
	 */
	@Column(name = "userRegisteredTime")
	private Timestamp userRegisteredTime;
	
	/**
	 * 定义用户修改时间
	 */
	@Column(name = "userModifyTime")
	private Timestamp userModifyTime;
	
	/**
	 * 定义用户退出时间
	 */
	@Column(name = "userLogoutTime")
	private Timestamp userLogoutTime;
	
	/**
	 * 定义用户使用的设备
	 */
	@Column(name = "userUseDevice")
	private String userUseDevice;
	
	/**
	 * 定义用户登录类型
	 */
	@Column(name = "userLoginType")
	private String userLoginType;
	
	/**
	 * 定义用户在线时长
	 */
	@Column(name = "userOnlineTime")
	private Timestamp userOnlineTime;

	/**
	 * 定义用户邮箱 
	 */
	@Column(name = "userEmail")
	private String userEmail;
	
	/**
	 * 实现用户Id的get方法
	 * @return
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 实现用户Id的set方法
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 实现用户名的get方法
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 实现用户名的set方法
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 实现用户名密码的get方法
	 * @return
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * 实现用户密码的set方法
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 实现用户登录状态的get方法
	 * @return
	 */
	public String getUserLoginStatus() {
		return userLoginStatus;
	}

	/**
	 * 实现用户登录状态的set方法
	 * @param userLoginStatus
	 */
	public void setUserLoginStatus(String userLoginStatus) {
		this.userLoginStatus = userLoginStatus;
	}

	/**
	 * 实现用户的登录时间的get方法
	 * @return
	 */
	public Timestamp getUserLoginTime() {
		return userLoginTime;
	}

	/**
	 * 实现用户登录时间的set方法
	 * @param userLoginTime
	 */
	public void setUserLoginTime(Timestamp userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

	/**
	 * 实现用户注册时间的get方法
	 * @return
	 */
	public Timestamp getUserRegisteredTime() {
		return userRegisteredTime;
	}

	/**
	 * 实现用户注册时间的set方法
	 * @param userRegisteredTime
	 */
	public void setUserRegisteredTime(Timestamp userRegisteredTime) {
		this.userRegisteredTime = userRegisteredTime;
	}

	/**
	 * 实现用户修改时间的get方法
	 * @return
	 */
	public Timestamp getUserModifyTime() {
		return userModifyTime;
	}

	/**
	 * 实现用户修改时间的set方法
	 * @param userModifyTime
	 */
	public void setUserModifyTime(Timestamp userModifyTime) {
		this.userModifyTime = userModifyTime;
	}

	/**
	 * 实现用户退出时间的get方法
	 * @return
	 */
	public Timestamp getUserLogoutTime() {
		return userLogoutTime;
	}

	/**
	 * 实现用户退出时间的set方法
	 * @param userLogoutTime
	 */
	public void setUserLogoutTime(Timestamp userLogoutTime) {
		this.userLogoutTime = userLogoutTime;
	}

	/**
	 * 实现用户使用设备的get方法
	 * @return
	 */
	public String getUserUseDevice() {
		return userUseDevice;
	}

	/**
	 * 实现用户使用设备的set方法
	 * @param userUseDevice
	 */
	public void setUserUseDevice(String userUseDevice) {
		this.userUseDevice = userUseDevice;
	}

	/**
	 * 实现用户登录类型的get方法
	 * @return
	 */
	public String getUserLoginType() {
		return userLoginType;
	}

	/**
	 * 实现用户登录类型的set方法
	 * @param userLoginType
	 */
	public void setUserLoginType(String userLoginType) {
		this.userLoginType = userLoginType;
	}

	/**
	 * 实现用户在线时长的get方法
	 * @return
	 */
	public Timestamp getUserOnlineTime() {
		return userOnlineTime;
	}

	/**
	 * 实现用户在线时长的set方法
	 * @param userOnlineTime
	 */
	public void setUserOnlineTime(Timestamp userOnlineTime) {
		this.userOnlineTime = userOnlineTime;
	}

	/**
	 * 实现用户邮箱的get方法
	 * @return
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 实现用户邮箱的set方法
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
