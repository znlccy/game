package com.youda.serviceImpl;

import com.youda.dao.ChannelMapper;
import com.youda.dao.GameChannelMapper;
import com.youda.dao.GameMapper;
import com.youda.encrypt.SHAEncrpt;
import com.youda.model.Channel;
import com.youda.model.ChannelUser;
import com.youda.model.Game;
import com.youda.model.GameChannel;
import com.youda.request.channel.GameRequest;
import com.youda.request.channel.LoginRequest;
import com.youda.request.channel.RegisterRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.statistics.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.youda.service.ChannelService;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义游戏渠道服务接口实现类
 */

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelMapper channelMapper;
    @Autowired
    GameChannelMapper gameChannelMapper;
    @Autowired
    GameMapper gameMapper;

    @Override
    public ResponseEntity register(RegisterRequest request) {
        ChannelUser user = channelMapper.findAllChannelUser(request.getPhone());
        if (user != null) {
            return ResponseStatusCode.conflictError();
        }
        Channel channel = new Channel();
        channel.setChannelLabel(request.getChannelLabel());
        channel.setChannelName(request.getChannelName());
        channel.setChannelWebSite(request.getChannelWebSite());
        channelMapper.addChannel(channel);
        if (channel.getChannelId() != 0) {
            user = new ChannelUser();
            user.setChannelId(channel.getChannelId());
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.setPassword(SHAEncrpt.SHAEncrption(request.getPassword()));
            user.setPhone(request.getPhone());
            user.setToken(UUID.randomUUID().toString());
            channelMapper.addChannelUser(user);
            if (user.getChannelUserId() != 0L) {
                TokenResponse tokenResponse = new TokenResponse();
                tokenResponse.setChannelId(channel.getChannelId());
                tokenResponse.setToken(user.getToken());
                tokenResponse.setUserName(channel.getChannelName());
                tokenResponse.setIsRoot(user.getIsRoot());
                tokenResponse.setChannelUserId(user.getChannelUserId());
                return ResponseStatusCode.postSuccess(tokenResponse);

            }


        }
        return ResponseStatusCode.conflictError();
    }

    @Override
    public ResponseEntity login(LoginRequest request) {
        ChannelUser user = channelMapper.findAllChannelUser(request.getPhone());
        if (user == null) return ResponseStatusCode.notFindError();
        if (SHAEncrpt.SHAEncrption(request.getPassword()).equals(user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setPassword("");
            Channel channel = channelMapper.findByChannelId(user.getChannelId());
            channelMapper.updateToken(user);
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setChannelId(channel.getChannelId());
            tokenResponse.setToken(user.getToken());
            tokenResponse.setUserName(channel.getChannelName());
            tokenResponse.setChannelUserId(user.getChannelUserId());
            tokenResponse.setIsRoot(user.getIsRoot());
            return ResponseStatusCode.putOrGetSuccess(tokenResponse);

        }
        return ResponseStatusCode.verifyError();
    }

    @Override
    public ResponseEntity bindGame(long channelId, long gameId) {
        GameChannel gameChannel = gameChannelMapper.findByIds(gameId, channelId);
        if (gameChannel != null) {
            return ResponseStatusCode.conflictError();
        }
        gameChannel = new GameChannel();
        gameChannel.setAppKey(SHAEncrpt.SHAEncrption(UUID.randomUUID().toString()));
        gameChannel.setGameId(gameId);
        gameChannel.setChannelId(channelId);
        gameChannelMapper.add(gameChannel);
        return ResponseStatusCode.postSuccess(gameChannel);
    }

    @Override
    public ResponseEntity getAllGame(Long channelId) {
        List<GameChannel> list = gameChannelMapper.findByChannel(channelId);
        return ResponseStatusCode.putOrGetSuccess(list);
    }

    @Override
    public ResponseEntity addGame(Long channelId, GameRequest gameRequest) {
        Game game = new Game();
        game.setGameName(gameRequest.getGameName());
        game.setGamePackage(gameRequest.getGamePackage());
        gameMapper.addGame(game);
        if (game.getGameId() != 0) {
            return bindGame(channelId, game.getGameId());

        }
        return ResponseStatusCode.cashError();
    }

    @Override
    public ChannelUser findUserById(Long channelUserId) {
        return channelMapper.findChannelById(channelUserId);
    }

    @Override
    public ResponseEntity getChannels() {
        return ResponseStatusCode.putOrGetSuccess(channelMapper.findAllChannel());
    }
}
