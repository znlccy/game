package com.youda.model.statistics;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Chencongye
 * @Date 2017/12/29 15:49
 * @Version 1.0.0
 * @Instructions 用于用户统计
 */

@Entity
@Table(name = "tb_user_caculator",catalog = "db_ydgame")
public class UserCaculator {

    /*声明用户统计主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userCaculatorId")
    private Long userCaculatorId;

    /*声明用户登录的游戏渠道*/
    @Column(name = "gameChannelId")
    private Long gameChannelId;

    /*声明用户登录的主键*/
    @Column(name = "userId")
    private Long userId;

    /*声明用户登录时间*/
    @Column(name = "userLoginTime")
    private Timestamp userLoginTime;

    /*声明用户使用的设备*/
    @Column(name = "userUseDevice")
    private String userUseDevice;

    /*声明用户注册时间*/
    @Column(name = "userRegistedTime")
    private Timestamp userRegistedTime;

    /*实现用户统计主键的get方法*/
    public Long getUserCaculatorId() {
        return userCaculatorId;
    }

    /*实现用户统计主键的set方法*/
    public void setUserCaculatorId(Long userCaculatorId) {
        this.userCaculatorId = userCaculatorId;
    }

    /*实现用户统计游戏渠道的get方法*/
    public Long getGameChannelId() {
        return gameChannelId;
    }

    /*实现用户统计游戏渠道的set方法*/
    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    /*实现用户统计用户主键的get方法*/
    public Long getUserId() {
        return userId;
    }

    /*实现用户统计用户主键的set方法*/
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /*实现用户统计用户登录时间的get方法*/
    public Timestamp getUserLoginTime() {
        return userLoginTime;
    }

    /*实现用户统计登录时间的set方法*/
    public void setUserLoginTime(Timestamp userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    /*实现用户统计登录设备的get方法*/
    public String getUserUseDevice() {
        return userUseDevice;
    }

    /*实现用户统计登录设备的set方法*/
    public void setUserUseDevice(String userUseDevice) {
        this.userUseDevice = userUseDevice;
    }

    /*实现用户统计用户注册时间的get方法*/
    public Timestamp getUserRegistedTime() {
        return userRegistedTime;
    }

    /*实现用户统计用户注册时间的set方法*/
    public void setUserRegistedTime(Timestamp userRegistedTime) {
        this.userRegistedTime = userRegistedTime;
    }
}
