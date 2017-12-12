package com.youda.serviceImpl;

import com.youda.dao.UserGameMapper;
import com.youda.service.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGameServiceImpl implements UserGameService {

    @Autowired
    UserGameMapper userGameMapper;

    @Override
    public void userLoginGame(long userId, long gameId) {
        userGameMapper.userLoginGame(userId,gameId);
    }

}
