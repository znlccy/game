package com.youda.timertask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 读取配置数据库 的配置文件
 */

@Configuration
public class ReadConfiguration {
	
	@Value("db_ydgame")
	private String database;
	
	@Value("3306")
	private int port;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	/*@Value("yd123456")
	private String password;*/
	
	@Value("${spring.backhost}")
	private String host;

	/**
	 * 实现获取数据库名称的get方法
	 * @return
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * 实现获取数据库名称的set方法
	 * @param database
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * 实现获取端口名称的get方法
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 实现获取端口名称的set方法
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 实现获取用户名的get方法
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 实现获取用户名的set方法
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 实现获取连接密码的get方法
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 实现获取密码的set方法
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 实现获取主机名称的get方法
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 实现获取主机的set方法
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
}
