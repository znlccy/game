package com.youda.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Chencongye
 * @Date 2017/12/27 9:17
 * @Version 1.0.0
 * @Instructions
 */
@Entity
@Table(name = "tb_applepayconf",catalog = "db_ydgame")
public class ApplePayConf {

    /*声明苹果支付的主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applePayConfId")
    private Long applePayConfId;

    /*声明游戏名称*/
    @Column(name = "gameName")
    private String gameName;

    /*声明异步通知地址*/
    @Column(name = "notifyUrl")
    private String notifyUrl;

    /*声明创建时间*/
    @Column(name = "createTime")
    private Date createTime;

    /*实现苹果支付主键的get方法*/
    public Long getApplePayConfId() {
        return applePayConfId;
    }

    /*实现苹果支付主键的set方法*/
    public void setApplePayConfId(Long applePayConfId) {
        this.applePayConfId = applePayConfId;
    }

    /*实现游戏名称的get方法*/
    public String getGameName() {
        return gameName;
    }

    /*实现游戏名称的set方法*/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /*实现异步通知地址的get方法*/
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /*实现异步通知地址的set方法*/
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /*实现创建时间的get方法*/
    public Date getCreateTime() {
        return createTime;
    }

    /*实现创建时间的set方法*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
