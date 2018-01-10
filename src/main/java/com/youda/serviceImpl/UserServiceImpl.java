package com.youda.serviceImpl;

import com.youda.dao.MessageAuthCodeMapper;
import com.youda.dao.SignUserMapper;
import com.youda.dao.TokenMapper;
import com.youda.dao.UserMapper;
import com.youda.dao.statistics.UserCaculatorMapper;
import com.youda.encrypt.SHAEncrpt;
import com.youda.model.MessageAuthCode;
import com.youda.model.SignUser;
import com.youda.model.Token;
import com.youda.model.User;
import com.youda.model.statistics.UserCaculator;
import com.youda.request.api.ForgetFirstRequest;
import com.youda.request.api.ForgetSecondRequest;
import com.youda.request.api.LoginRequest;
import com.youda.request.api.RegisterRequest;
import com.youda.request.channel.SignRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.response.api.TokenResponse;
import com.youda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    @Autowired
    private SignUserMapper signUserMapper;
    @Autowired
    private UserCaculatorMapper userCaculatorMapper;

//    /**
//     * 实现自动依赖注入字符型Redis模板
//     */
//    @Autowired
//    public StringRedisTemplate stringRedisTemplate;
//    /**
//     * 实现RedisTemplate模板自动依赖注入
//     */
//    @Autowired
//    private RedisTemplate redisTemplate;

    /**
     * 实现用户登录功能
     * (non-Javadoc)
     *
     * @see com.youda.service.UserService
     */
    @Override
    public ResponseEntity login(LoginRequest loginRequest, Long gameChannelId, String userUseDevice) {
        User user = userMapper.findByUserName(loginRequest.getUserName());

        if (user != null && SHAEncrpt.SHAEncrption(loginRequest.getUserPassword()).equals(user.getUserPassword())) {

            initUserCaculator(gameChannelId, user, userUseDevice);
            return ResponseStatusCode.putOrGetSuccess(addToken(user.getUserId(), loginRequest.getGameChannelId()));
        }
        return ResponseStatusCode.verifyError();
    }

    private void initUserCaculator(Long gameChannelId, User user, String userUseDevice) {
        UserCaculator userCaculator = new UserCaculator();
        userCaculator.setGameChannelId(gameChannelId);
        userCaculator.setUserId(user.getUserId());
        userCaculator.setUserLoginTime(new Timestamp(System.currentTimeMillis()));
        userCaculator.setUserRegistedTime(user.getUserRegisteredTime());
        userCaculator.setUserUseDevice(userUseDevice);
        userCaculatorMapper.addUserCaculator(userCaculator);
    }

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

    /*实现新增用户统计*/
    @Override
    public ResponseEntity signUser(SignRequest request, String userUseDevice) {
        SignUser signUser = signUserMapper.findBySign(request.getSignWith(), request.getSign());
        Long id;
        if (signUser == null) {
            User user = new User();
            user.setUserRegisteredTime(new Timestamp(System.currentTimeMillis()));
            userMapper.addUser(user);
            signUser = new SignUser();
            signUser.setSign(request.getSign());
            signUser.setSignId(request.getSignWith());
            signUser.setUserId(user.getUserId());
            signUserMapper.addSignUser(signUser);
            id = user.getUserId();
        } else {
            id = signUser.getUserId();
        }
        initUserCaculator(request.getGameChannelId(), userMapper.findByUserId(signUser.getUserId()), userUseDevice);
        return ResponseStatusCode.putOrGetSuccess(addToken(id, request.getGameChannelId()));
    }

    private TokenResponse addToken(Long userId, Long getGameChannelId) {
        Token token = new Token();
        token.setAccessToken(UUID.randomUUID().toString());
        token.setUserId(userId);
        token.setExpirationTime(new Timestamp(System.currentTimeMillis()));
        token.setGameChannelId(getGameChannelId);
        tokenMapper.delTokenByIds(token);
        tokenMapper.addToken(token);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token.getAccessToken());
        tokenResponse.setUserId(userId);
        return tokenResponse;
    }

}
