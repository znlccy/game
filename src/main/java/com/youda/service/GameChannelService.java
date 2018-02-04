package com.youda.service;

import com.youda.model.GameChannel;
import com.youda.request.api.GameChannelRequest;
import com.youda.response.api.GameChannelResponse;

/**
 * @CreateTime:2018/2/4 11:53
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 游戏渠道服务接口类
 */

public interface GameChannelService {

    /*声明获取游戏渠道*/
    GameChannel findByIds(Long gameChannelId);

    /*声明获取google的游戏渠道*/
    GameChannelResponse generateAppKey(GameChannelRequest gameChannelRequest);

}
