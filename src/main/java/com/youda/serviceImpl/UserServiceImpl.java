package com.youda.serviceImpl;

import com.youda.dao.UserMapper;
import com.youda.encrypt.SHAEncrpt;
import com.youda.interceptor.ResponseStatusCode;
import com.youda.model.Token;
import com.youda.model.User;
import com.youda.request.LoginRequest;
import com.youda.request.RegisterRequest;
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
     * 定义日志打印输出变量
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 实现UserMapper的自动依赖注入
     */
    @Autowired
    private UserMapper userMapper;

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
            token.setUser(user);
            userMapper.addToken(token);
            ValueOperations<String, Token> operations = redisTemplate.opsForValue();
            operations.set("token_" + user.getUserName(), token);
            return ResponseStatusCode.putOrGetSuccess(token);
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

    /*
     * 实现忘记密码的第一步
     * (non-Javadoc)
     * @see com.youda.service.UserService#forgotPasswordStart()
     */
    @Override
    public ResponseEntity forgotPasswordStart() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * 实现忘记密码的第二步
     * (non-Javadoc)
     * @see com.youda.service.UserService#forgotPasswordEnd()
     */
    @Override
    public ResponseEntity forgotPasswordEnd() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * 实现用户获取验证码
     * (non-Javadoc)
     * @see com.youda.service.UserService#getVerificationCode()
     */
    @Override
    public ResponseEntity getVerificationCode() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * 实现用户主键Id来查询单个用户,查询得到之后，把数据插入到Redis缓存中
     * (non-Javadoc)
     * @see com.youda.service.UserService#getUserByUserId(java.lang.String)
     */
    public User getUserByUserId(long userId) {
        //从缓存中获取用户信息
        String key = "user_" + userId;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        //判断缓存是否存在
        boolean hasKey = redisTemplate.hasKey(key);

        //判断缓存是否存在，如果存在，直接从缓存中获取，如果不存在从数据库中查询
        if (hasKey) {
            //获取键对应的值
            User user = operations.get(key);
            if (user == null) {
                ResponseStatusCode.notFindError();
                return null;
            } else {
                //日志打印输出
                LOGGER.info("UserServiceImpl.getUserByUserId():从Redis缓存中获取用户信息>>" + user.toString());
                //返回数据
                return user;
            }
        } else {
            //缓存不存在，从MySQL数据库中查询获取,也就是从DB中获取
            User user = userMapper.findByUserId(userId);
            if (user == null) {
                ResponseStatusCode.notFindError();
                return null;
            } else {
                //插入到缓存中
                operations.set(key, user);
                LOGGER.info("UserServiceImpl.findUserByUserId():从数据库中获取用户信息并把用户插入缓存 >>" + user.toString());
                //返回数据
                return user;
            }
        }
    }

    /*
     * 实现通过用户名称来获取单个用户
     * (non-Javadoc)
     * @see com.youda.service.UserService#findUserByUserName(java.lang.String)
     */
    @Override
    public User findUserByUserName(String userName) {
        // TODO Auto-generated method stub
        String key = "userName_" + userName;
        /*设置添加值得类型*/
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        //判断数据库中是否有这个键值
        boolean hasKey = redisTemplate.hasKey(key);
        //判断缓存是否存在，如果存在从缓存中
        if (hasKey) {
            //缓存存在，那么就慈宁宫
            User user = operations.get(key);
            if (user == null) {
                return null;
            } else {
                //输出日志信息
                /*LOGGER.info("UserServiceImpl.findByUserName():从Redis缓存中获取用户信息:" + user.toString());*/
                //返回用户信息
                return user;
            }

        } else {
            //缓存不存在，那么就从MySQL数据库中获取
            User user = userMapper.findByUserName(userName);
            //把用户信息保存到Redis中
            operations.set(key, user);
            /*LOGGER.info("UserServiceImpl.findByUserName():从MySQL数据中获取用户信息:" + user.toString());*/
            return user;
        }
    }

    /*
     * 实现通过用户Id来修改用户信息
     * (non-Javadoc)
     * @see com.youda.service.UserService#modifyByUserId(java.lang.String)
     */
    @Override
    public boolean modifyByUserId(long userId) {
        // TODO Auto-generated method stub
        User user = userMapper.findByUserId(userId);
        System.err.println("user：" + user);
        if (user == null) {
            return false;
        } else {
            user.setUserName("xiaolong");
            boolean result = userMapper.modifyUserInfo(user);
            if (result) {
                return true;
            } else {
                return false;
            }
        }
    }

    /*
     * 实现通过用户名来修改用户信息
     * (non-Javadoc)
     * @see com.youda.service.UserService#modifyByUserName(java.lang.String)
     */
    @Override
    public boolean modifyByUserName(String userName) {
        // TODO Auto-generated method stub
        User user = this.findUserByUserName(userName);
        user.setUserName("nihao");
        userMapper.modifyUserInfo(user);

		/*查看缓存是否存在*/
        String key = "userName_" + userName;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("UserServiceImpl.modifyByUserName():从Redis缓存中删除用户信息：" + user.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * 实现通过用户主键来删除用户信息
     *
     * @param userId
     * @return
     */
    public boolean deleteByUserId(long userId) {

        boolean result = userMapper.deleteByUserId(userId);

        String key = "user_" + userId;

        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("UserServiceImpl.deleteByUserId():从Redis缓存数据库中删除用户信息>>" + hasKey);
        }

        return result;
    }

    /*
     * 实现验证登录token的功能
     * (non-Javadoc)
     * @see com.youda.service.UserService#validateAccessToken(java.lang.String)
     */
    @Override
    public boolean validateAccessToken(String accessToken) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * 实现查找所有用户的功能
     * (non-Javadoc)
     * @see com.youda.service.UserService#findAllUser()
     */
    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        return userMapper.findAllUser();
    }
}
