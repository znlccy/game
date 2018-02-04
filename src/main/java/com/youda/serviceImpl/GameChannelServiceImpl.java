
package com.youda.serviceImpl;

import com.youda.dao.*;
import com.youda.model.*;
import com.youda.request.api.GameChannelRequest;
import com.youda.response.api.GameChannelResponse;
import com.youda.service.GameChannelService;
import com.youda.service.GameService;
import com.youda.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    /*声明游戏映射接口的自动依赖注入*/
    @Autowired
    private GameMapper gameMapper;

    /*声明渠道映射接口的自动依赖注入*/
    @Autowired
    private ChannelMapper channelMapper;

    /*声明google支付映射接口的自动依赖注入*/
    @Autowired
    private GooglePayConfMapper googlePayConfMapper;

    /*声明apple支付映射接口的自动依赖注入*/
    @Autowired
    private ApplePayConfMapper applePayConfMapper;

    @Override
    public GameChannel findByIds(Long gameChannelId) {
        return gameChannelMapper.findById(gameChannelId);
    }

    @Override
    public GameChannelResponse generateAppKey(GameChannelRequest gameChannelRequest) {
        Game game = new Game();
        Long gameId;
        Long channelId;
        String gameName = gameChannelRequest.getGameName();
        String gamePackage = gameChannelRequest.getGamePackage();
        game.setGameName(gameName);
        game.setGamePackage(gamePackage);
        if (gameMapper.findByGameName(gameName)!=null)
        {
            gameId = gameMapper.findByGameName(gameName).getGameId();
        }
        else
        {
            gameMapper.addGame(game);
            gameId = game.getGameId();
        }

        Channel channel = new Channel();
        channel.setChannelName(gameChannelRequest.getChannelName());
        channel.setChannelLabel(gameChannelRequest.getChannelName());
        String channelName = gameChannelRequest.getChannelName();
        if (channelMapper.findByChannelName(gameChannelRequest.getChannelName())!=null) {
            channelId = channelMapper.findByChannelName(gameChannelRequest.getChannelName()).getChannelId();
        }
        else
        {
            channelMapper.addChannel(channel);
            channelId = channel.getChannelId();
        }

        String appKey = MD5Util.MD5Encode(gameName+gameChannelRequest.getChannelName(),"utf8");
        GameChannel gameChannel = new GameChannel();
        gameChannel.setGameId(gameId);
        gameChannel.setChannelId(channelId);
        gameChannel.setAppKey(appKey);
        gameChannelMapper.add(gameChannel);

        ApplePayConf applePayConf = new ApplePayConf();
        applePayConf.setCreateTime(new Timestamp(System.currentTimeMillis()));
        applePayConf.setGameChannelId(gameChannel.getGameChannelId());
        applePayConf.setNotifyUrl(gameChannelRequest.getNotifyUrl());
        applePayConfMapper.addApplePayConf(applePayConf);

        GooglePayConf googlePayConf = new GooglePayConf();
        googlePayConf.setCreateTime(new Timestamp(System.currentTimeMillis()));
        googlePayConf.setGameChannelId(gameChannel.getGameChannelId());
        googlePayConf.setNotifyUrl(gameChannelRequest.getNotifyUrl());
        googlePayConf.setSignNature(" ");
        googlePayConfMapper.addGooglePayConf(googlePayConf);

        GameChannelResponse gameChannelResponse = new GameChannelResponse();
        gameChannelResponse.setAppKey(appKey);
        gameChannelResponse.setGameChannelId(gameChannel.getGameChannelId());
        gameChannelResponse.setNotifyUrl(gameChannelRequest.getNotifyUrl());

        return gameChannelResponse;
    }

}
