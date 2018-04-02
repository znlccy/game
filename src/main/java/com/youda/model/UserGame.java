package com.youda.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Chencongye
 * @Date 2017/12/8 18:57
 * @Version 1.0.0
 * @Instructions 实现用户和游戏之间的多对多关系
 */

@Entity
@Table(name = "tb_usergame")
public class UserGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userGameId")
    private long userGameId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "keepWord")
    private String keepWord;

    public long getUserGameId() {
        return userGameId;
    }

    public void setUserGameId(long userGameId) {
        this.userGameId = userGameId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getKeepWord() {
        return keepWord;
    }

    public void setKeepWord(String keepWord) {
        this.keepWord = keepWord;
    }
}
