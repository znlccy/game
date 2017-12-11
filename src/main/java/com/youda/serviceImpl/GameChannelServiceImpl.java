
package com.youda.serviceImpl;

import com.youda.dao.GameChannelMapper;
import com.youda.dao.GameMapper;
import com.youda.model.Game;
import com.youda.model.GameChannel;
import com.youda.service.GameChannelService;
import com.youda.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现游戏服务接口
 */

@Service
public class GameChannelServiceImpl implements GameChannelService {

    /**
     * 实现游戏映射接口的自动依赖注入
     */
    @Autowired
    private GameChannelMapper gameChannelMapper;

    @Override
    public GameChannel findByIds(Long gameChannelId) {
        return gameChannelMapper.findById(gameChannelId);
    }
}
