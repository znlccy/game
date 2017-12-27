package com.youda.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Chencongye
 * @Date 2017/12/27 9:16
 * @Version 1.0.0
 * @Instructions 声明google支付的配置文件
 */

@Entity
@Table(name = "tb_googlepayconf",catalog = "db_ydgame")
public class GooglePayConf {

    /*声明google配置的主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "googlePayConfId")
    private Long googlePayConfId;

    /*声明游戏名称*/
    @Column(name = "gameChannelId")
    private Long gameChannelId;

    /*声明异步通知地址*/
    @Column(name = "notifyUrl",length = 800)
    private String notifyUrl;

    /*声明创建时间*/
    @Column(name = "createTime")
    private Date createTime;

    /*声明签名文件*/
    @Column(name = "signNature",length = 2000)
    private String signNature;

    /*实现google支付配置主键的get方法*/
    @NotBlank(message = "google支付配置主键不能为空")
    public Long getGooglePayConfId() {
        return googlePayConfId;
    }

    /*实现google支付配置主键的set方法*/
    public void setGooglePayConfId(Long googlePayConfId) {
        this.googlePayConfId = googlePayConfId;
    }

    /*实现游戏渠道的get方法*/
    @NotBlank(message = "游戏渠道主键不能为空")
    public Long getGameChannelId() {
        return gameChannelId;
    }

    /*实现游戏渠道的set方法*/
    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    /*实现异步通知的地址的get方法*/
    @NotBlank(message = "异步通知地址为空")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /*实现异步通知的地址的set方法*/
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /*实现创建时间的get方法*/
    @NotBlank(message = "创建时间为空")
    public Date getCreateTime() {
        return createTime;
    }

    /*实现创建时间的set方法*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /*实现签名文件的get方法*/
    public String getSignNature() {
        return signNature;
    }

    /*声明签名文件的set方法*/
    public void setSignNature(String signNature) {
        this.signNature = signNature;
    }
}
