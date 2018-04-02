package com.youda.model.statistics;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @CreateTime:2018/3/26 11:26
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 下载统计实体类
 */

@Entity
@Table(name = "tb_download", catalog = "db_ydgame")
public class Download implements Serializable {

    /**
     * 声明主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 声明ip地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 声明用户编号
     */
    @Column(name = "uid")
    private String uid;

    /**
     * 声明下载平台
     */
    @Column(name = "platform")
    private String platform;

    /**
     * 声明时间
     */
    @Column(name = "time")
    private Timestamp time;

    /**
     * @comment: getId 实现获得主键
     * @param: []
     * @return: java.lang.Long
     */
    public Long getId() {
        return id;
    }

    /**
     * @comment: setId 实现设置主键
     * @param: [id]
     * @return: void
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @comment: getIp 实现获得IP
     * @param: []
     * @return: java.lang.String
     */
    public String getIp() {
        return ip;
    }

    /**
     * @comment: setIp 实现设置IP
     * @param: [ip]
     * @return: void
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @comment: getUid 实现获得用户主键
     * @param: []
     * @return: java.lang.String
     */
    public String getUid() {
        return uid;
    }

    /**
     * @comment: setUid 实现设置主键
     * @param: [uid]
     * @return: void
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @comment: getPlatform 实现获取平台
     * @param: []
     * @return: java.lang.String
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @comment: setPlatform 实现设置平台
     * @param: [platform]
     * @return: void
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @comment: getTime 实现获得时间
     * @param: []
     * @return: java.sql.Timestamp
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @comment: setTime 实现设置时间
     * @param: [time]
     * @return: void
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
