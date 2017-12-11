package com.youda.service;

import com.youda.model.GameChannel;

public interface GameChannelService {
    GameChannel findByIds(Long gameChannelId);
}
