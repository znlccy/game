package com.youda.request.api;

import com.youda.model.Channel;

/**
 * @CreateTime:2018/2/5 14:28
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 生成门禁实体类
 */

public class ChannelKeyRequest {

    /**
     * 声明加入语音频道
     */
    private String channel;

    /**
     * @comment: getChannel实现获取语音频道
     * @param: []
     * @return: java.lang.String
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @comment: setChannel实现设置语音频道
     * @param: [channel]
     * @return: void
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }
}
