package com.youda.request.api;

import com.youda.model.Channel;

/**
 * @CreateTime:2018/2/5 14:28
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 生成门禁实体类
 */

public class ChannelKeyRequest {

    private String appId;

    private Long gameChannelId;

    private String channel;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getGameChannelId() {
        return gameChannelId;
    }

    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
