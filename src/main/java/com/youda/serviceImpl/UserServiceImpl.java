package com.youda.serviceImpl;

import com.youda.dao.MessageAuthCodeMapper;
import com.youda.dao.TokenMapper;
import com.youda.dao.UserMapper;
import com.youda.encrypt.SHAEncrpt;
import com.youda.model.MessageAuthCode;
import com.youda.request.ForgetFirstRequest;
import com.youda.request.ForgetSecondRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.model.Token;
import com.youda.model.User;
import com.youda.request.LoginRequest;
import com.youda.request.RegisterRequest;
import com.youda.response.TokenResponse;
import com.youda.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现用户服务接口中的功能
 */

@Service
public class UserServiceImpl implements UserService {

    /**
     * 实现UserMapper的自动依赖注入
     */
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageAuthCodeMapper codeMapper;
    @Autowired
    private TokenMapper tokenMapper;
    /**
     * 实现自动依赖注入字符型Redis模板
     */
    @Autowired
    public StringRedisTemplate stringRedisTemplate;
    /**
     * 实现RedisTemplate模板自动依赖注入
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 实现用户登录功能
     * (non-Javadoc)
     *
     * @see com.youda.service.UserService
     */
    @Override
    public ResponseEntity login(LoginRequest loginRequest) {
        User user = userMapper.findByUserName(loginRequest.getUserName());
        if (user != null && SHAEncrpt.SHAEncrption(loginRequest.getUserPassword()).equals(user.getUserPassword())) {
            Token token = new Token();
            token.setAccessToken(UUID.randomUUID().toString());
            token.setUserId(user.getUserId());
            token.setExpirationTime(new Timestamp(System.currentTimeMillis()));
            token.setGameChannelId(loginRequest.getGameChannelId());
            tokenMapper.addToken(token);
//            if (token.getTokenId() != 0) {
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token.getAccessToken());
            tokenResponse.setUserId(user.getUserId());
            return ResponseStatusCode.putOrGetSuccess(tokenResponse);

        }
        return ResponseStatusCode.verifyError();
    }

    /*
     * 实现用户注册的功能
     * (non-Javadoc)
     * @see com.youda.service.UserService#registered()
     */
    @Override
    public ResponseEntity register(RegisterRequest register) {
        if (userMapper.findByUserName(register.getUserName()) != null) return ResponseStatusCode.conflictError();
        User user = new User();
        user.setUserName(register.getUserName());
        user.setUserPassword(SHAEncrpt.SHAEncrption(register.getUserPassword()));
        user.setUserRegisteredTime(new Timestamp(System.currentTimeMillis()));
        userMapper.addUser(user);
        return ResponseStatusCode.putOrGetSuccess(null);
    }

    @Override
    public ResponseEntity forgotPasswordStart(ForgetFirstRequest request) {
        if (userMapper.findByUserName(request.getUserName()) == null)
            return ResponseStatusCode.notFindError();
        MessageAuthCode code = codeMapper.findByMacodeContent(request.getUserName());
        if (code == null || !request.getVerificationCode().equals(code.getMacodeContent()))
            return ResponseStatusCode.verifyError();

        codeMapper.deleteByMacodePhone(request.getUserName());

        return ResponseStatusCode.putOrGetSuccess(null);
    }

    /**
     * 实现忘记密码的第二步
     * (non-Javadoc)
     */
    @Override
    public ResponseEntity forgotPasswordEnd(ForgetSecondRequest request) {
        User user = userMapper.findByUserName(request.getUserName());
        if (user == null) return ResponseStatusCode.verifyError();
        user.setUserPassword(SHAEncrpt.SHAEncrption(request.getUserPassword()));
        userMapper.modifyUserInfo(user);
        tokenMapper.deleteByUserId(user.getUserId());
        return ResponseStatusCode.putOrGetSuccess(null);
    }

}
