package com.youda.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Chencongye
 * @Date 2017/12/8 18:32
 * @Version 1.0.0
 * @Instructions 游戏和渠道的表
 */

@Entity
@Table(name = "tb_gamechannel",catalog = "db_ydgame")
public class GameChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameChannelId")
    private long gameChannelId;

    @Column(name = "appKey")
    private String appKey;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "channelId")
    private Channel channel;

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
