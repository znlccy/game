package com.youda.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introce 定义用户验证机制实体类
 */

@Entity
@Table(name = "tb_token", catalog = "db_ydgame")
public class Token implements Serializable {

    /**
     * 定义token的主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenId")
    private long tokenId;

    /**
     * 定义token的内容
     */
    @Column(name = "accessToken")
    private String accessToken;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "gameChannelId")
    private Long gameChannelId;
    /**
     * 定义token的创建时间
     */
    @Column(name = "expirationTime")
    private Timestamp expirationTime;

//	/**
//	 * 定义用户和Token之间的一对一关系
//	 */
//	@ManyToOne
//	@JoinColumn(name = "userId")
//	private User user;

    /**
     * 实现token主键的get方法
     *
     * @return
     */
    public long getTokenId() {
        return tokenId;
    }

    /**
     * 实现token主键的set方法
     *
     * @param tokenId
     */
    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * 实现token内容的get方法
     *
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 实现token内容的set方法
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameChannelId() {
        return gameChannelId;
    }

    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    /**
     * 默认的无参构造方法
     */
    public Token() {
        // TODO Auto-generated constructor stub
    }

}
