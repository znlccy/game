package com.youda.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introce 定义用户验证机制实体类
 */

@Entity
@Table(name="tb_token", catalog="db_ydgame")
public class Token {

	/**
	 * 定义token的主键 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tokenId")
	private long tokenId;
	
	/**
	 * 定义token的内容
	 */
	@Column(name = "accessToken")
	private String accessToken;
	
	/**
	 * 定义token的创建时间
	 */
	@Column(name = "expirationTime")
	private Timestamp expirationTime;
	
	/**
	 * 定义用户和Token之间的一对一关系
	 */
	@OneToOne(mappedBy="token")
	private User user;

	/**
	 * 实现token主键的get方法
	 * @return
	 */
	public long getTokenId() {
		return tokenId;
	}

	/**
	 * 实现token主键的set方法
	 * @param tokenId
	 */
	public void setTokenId(long tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * 实现token内容的get方法
	 * @return
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * 实现token内容的set方法
	 * @param token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * 实现token过期的get方法
	 * @return
	 */
	public Timestamp getExpirationTime() {
		return expirationTime;
	}

	/**
	 * 实现token过期时间的set方法
	 * @param expirationTime
	 */
	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	/**
	 * 实现用户和Token之间的一对一关系的get方法
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 实现用户和token之间的一对一关系的set方法
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 默认的无参构造方法
	 */
	public Token() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 实现带有参数的构造方法
	 * @param tokenId
	 * @param accessToken
	 * @param expirationTime
	 * @param user
	 */
	public Token(long tokenId, String accessToken, Timestamp expirationTime, User user) {
		super();
		this.tokenId = tokenId;
		this.accessToken = accessToken;
		this.expirationTime = expirationTime;
		this.user = user;
	}
	
}
