package com.youda.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @CreateTime:2018/3/4 13:26
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 创建文件实体类
 */

@Entity
@Table(name = "tb_file", catalog = "db_ydgame")
public class FileOS {

    /**
     * 声明上传文件主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileId")
    private Long fileId;

    /**
     * 声明文件存放路径
     */
    @Column(name = "fileUrl")
    private String fileUrl;

    /**
     * 声明文件名
     */
    @Column(name = "fileName")
    private String fileName;

    /**
     * 声明文件创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 声明游戏渠道
     */
    @Column(name = "gameChannelId")
    private String gameChannelId;

    /**
     * @comment: getFileId 实现获得文件主键
     * @param: []
     * @return: java.lang.Long
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * @comment: setFileId 实现设置文件主键
     * @param: [fileId]
     * @return: void
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * @comment: getFileUrl 实现获取文件地址
     * @param: []
     * @return: java.lang.String
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @comment: setFileUrl 实现设置文件地址
     * @param: [fileUrl]
     * @return: void
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @comment: getFileName 实现获取文件名
     * @param: []
     * @return: java.lang.String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @comment: setFileName 实现设置文件名
     * @param: [fileName]
     * @return: void
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @comment: getCreateTime 实现获得文件创建时间
     * @param: []
     * @return: java.util.Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @comment: setCreateTime 实现设置文件创建时间
     * @param: [createTime]
     * @return: void
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @comment: getGameChannelId实现获得游戏渠道主键
     * @param: []
     * @return: java.lang.String
     */
    public String getGameChannelId() {
        return gameChannelId;
    }

    /**
     * @comment: setGameChannelId实现设置游戏渠道主键
     * @param: [gameChannelId]
     * @return: void
     */
    public void setGameChannelId(String gameChannelId) {
        this.gameChannelId = gameChannelId;
    }
}
