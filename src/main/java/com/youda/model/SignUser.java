package com.youda.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenshengyu
 * on 2017/12/14.
 */
@Entity
@Table(name = "tb_sign_user", catalog = "db_ydgame")
public class SignUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "signUserId")
    private long signUserId;

    @Column(name = "signId")
    private long signId;

    @Column(name = "sign")
    private String sign;

    @Column(name = "userId")
    private long userId;

    public long getSignUserId() {
        return signUserId;
    }

    public void setSignUserId(long signUserId) {
        this.signUserId = signUserId;
    }

    public long getSignId() {
        return signId;
    }

    public void setSignId(long signId) {
        this.signId = signId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
