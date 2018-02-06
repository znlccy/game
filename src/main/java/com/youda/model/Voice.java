package com.youda.model;

import javax.persistence.*;

/**
 * @CreateTime:2018/2/4 19:17
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 语音实体类
 */

@Entity
@Table(name = "tb_voice",catalog = "db_ydgame")
public class Voice {

    /*声明语音主键*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voiceId")
    private Long voiceId;

    /*声明应用主键*/
    @Column(name = "appId")
    private String appId;

    /*声明项目名称*/
    @Column(name = "gameChannelId")
    private String gameChannelId;

    /*声明应用证书*/
    @Column(name = "appCertificate")
    private String appCertificate;

    /*实现获取语音主键*/
    public Long getVoiceId() {
        return voiceId;
    }

    /*声明设置语音主键*/
    public void setVoiceId(Long voiceId) {
        this.voiceId = voiceId;
    }

    /*声明获取应用主键*/
    public String getAppId() {
        return appId;
    }

    /*声明设置应用主键*/
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /*声明获取项目主键*/
    public String getGameChannelId() {
        return gameChannelId;
    }

    /*声明设置项目主键*/
    public void setGameChannelId(String gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    /*声明获取应用证书*/
    public String getAppCertificate() {
        return appCertificate;
    }

    /*声明设置应用证书*/
    public void setAppCertificate(String appCertificate) {
        this.appCertificate = appCertificate;
    }
}
