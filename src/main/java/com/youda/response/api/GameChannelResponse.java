package com.youda.response.api;

import com.youda.response.BaseResponse;

public class GameChannelResponse implements BaseResponse {

    /*声明返回给客户端的gameChannelId*/
    private Long gameChannelId;

    /*声明返回给客户端的异步通知地址*/
    private String notifyUrl;

    /*声明返回的应用秘钥*/
    private String appKey;

    /*实现获取游戏渠道方法*/
    public Long getGameChannelId() {
        return gameChannelId;
    }

    /*实现设置游戏渠道方法*/
    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    /*实现获取异步通知地址*/
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /*实现设置异步通知地址*/
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /*实现获取应用秘钥*/
    public String getAppKey() {
        return appKey;
    }

    /*实现设置应用秘钥*/
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
