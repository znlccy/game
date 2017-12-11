package com.youda.service;

import com.youda.model.Token;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义token服务接口类
 */

public interface TokenService {
    Token findTokenByUserId(Long id);

    Token findTokenByIDs(Long userId, Long channelGameId);
}
