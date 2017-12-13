package com.youda.service;

import com.youda.request.channel.LoginRequest;
import com.youda.request.channel.RegisterRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义游戏渠道接口
 */

public interface ChannelService {

    ResponseEntity register(RegisterRequest request);

    ResponseEntity login(LoginRequest request);

    ResponseEntity bindGame(long channelId, long gameId);

    ResponseEntity getAllGame(Long channelId);

}
