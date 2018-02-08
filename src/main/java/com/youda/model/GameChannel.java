package com.youda.model;

import javax.persistence.*;

/**
 * @Author Chencongye
 * @Date 2017/12/8 18:32
 * @Version 1.0.0
 * @Instructions 游戏和渠道的表
 */

@Entity
@Table(name = "tb_gamechannel", catalog = "db_ydgame")
public class GameChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameChannelId")
    private long gameChannelId;

    @Column(name = "appKey")
    private String appKey;

    @Column(name = "gameId")
    private Long gameId;

    @Column(name = "channelId")
    private Long channelId;

    @Transient
    private Game game;

    @Transient
    private GooglePayConf googlePayConf;

    @Transient
    private ApplePayConf applePayConf;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getGameChannelId() {
        return gameChannelId;
    }

    public void setGameChannelId(long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public GooglePayConf getGooglePayConf() {
        return googlePayConf;
    }

    public void setGooglePayConf(GooglePayConf googlePayConf) {
        this.googlePayConf = googlePayConf;
    }

    public ApplePayConf getApplePayConf() {
        return applePayConf;
    }

    public void setApplePayConf(ApplePayConf applePayConf) {
        this.applePayConf = applePayConf;
    }
}
