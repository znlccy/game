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
    @Column(name = "gameName")
    private String gameName;

    /*声明异步通知地址*/
    @Column(name = "notifyUrl")
    private String notifyUrl;

    /*声明创建时间*/
    @Column(name = "createTime")
    private Date createTime;

    /*实现google配置的主键get方法*/
    @NotBlank(message = "Google配置表主键为空")
    public Long getGoogleConfId() {
        return googlePayConfId;
    }

    /*实现google配置主键的set方法*/
    public void setGoogleConfId(Long googlePayConfId) {
        this.googlePayConfId = googlePayConfId;
    }

    /*实现游戏名称的get方法*/
    @NotBlank(message = "游戏名为空")
    public String getGameName() {
        return gameName;
    }

    /*实现游戏名称的set方法*/
    public void setGameName(String gameName) {
        this.gameName = gameName;
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

}
