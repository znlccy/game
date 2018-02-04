package com.youda.request.api;

import com.youda.request.BaseRequest;

/**
 * @CreateTime:2018/2/4 11:18
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 基本的游戏渠道生成请求
 */

public class GameChannelRequest extends BaseRequest {

    /*声明游戏名称*/
    private String gameName;

    /*声明渠道名称*/
    private String channelName;

    /*声明游戏包名*/
    private String gamePackage;

    /*声明异步通知地址*/
    private String notifyUrl;

    /*实现获取游戏名称*/
    public String getGameName() {
        return gameName;
    }

    /*实现设置游戏名称*/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /*实现获取渠道名称*/
    public String getChannelName() {
        return channelName;
    }

    /*实现设置渠道名称*/
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /*实现获取游戏包名*/
    public String getGamePackage() {
        return gamePackage;
    }

    /*实现设置游戏包名*/
    public void setGamePackage(String gamePackage) {
        this.gamePackage = gamePackage;
    }

    /*实现获取异步通知地址*/
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /*实现设置异步通知地址*/
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /*实现判断字段是否为空*/
    @Override
    public boolean isEmpty() {
        return false;
    }
}
