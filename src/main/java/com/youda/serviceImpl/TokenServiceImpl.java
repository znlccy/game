package com.youda.serviceImpl;

import com.youda.dao.TokenMapper;
import com.youda.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youda.service.TokenService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义token服务接口的实现类
 */

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenMapper tokenMapper;
    @Override
    public Token findTokenByUserId(Long id) {
        return null;
    }

    @Override
    public Token findTokenByIDs(Long userId, Long channelGameId) {
        return tokenMapper.findTokenByIds(userId,channelGameId);
    }
}
