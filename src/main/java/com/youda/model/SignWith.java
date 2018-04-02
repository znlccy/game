package com.youda.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenshengyu
 * on 2017/12/14.
 */
@Entity
@Table(name = "tb_sign_with", catalog = "db_ydgame")
public class SignWith implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "signId")
    private long signId;

    @Column(name = "name")
    private String name;

    public long getSignId() {
        return signId;
    }

    public void setSignId(long signId) {
        this.signId = signId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
