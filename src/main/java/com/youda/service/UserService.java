package com.youda.service;

import com.youda.model.User;
import com.youda.request.LoginRequest;
import com.youda.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义用户服务接口相关约束，功能即规范
 */

public interface UserService {

    /**
     * 定义用户登录功能
     *
     * @return
     */
    ResponseEntity login(LoginRequest login);

    /**
     * 定义用户注册功能
     *
     * @return
     */
    ResponseEntity register(RegisterRequest register);

    /**
     * 定义忘记密码的第一步
     *
     * @return
     */
    ResponseEntity forgotPasswordStart();

    /**
     * 定义忘记密码的第二步
     *
     * @return
     */
    ResponseEntity forgotPasswordEnd();

    /**
     * 定义用户获取验证码
     *
     * @return
     */
    ResponseEntity getVerificationCode();

    /**
     * 定义通过userId查询用户
     *
     * @return
     */
    User getUserByUserId(long userId);

    /**
     * 定义通过userName查询用户
     *
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 定义通过用户I的修改用户信息
     *
     * @param userId
     * @return
     */
    boolean modifyByUserId(long userId);

    /**
     * 定义通过用户名修改用户信息
     *
     * @param userName
     * @return
     */
    boolean modifyByUserName(String userName);

    /**
     * 定义通过用户的主键Id来删除用户
     *
     * @param userId
     * @return
     */
    boolean deleteByUserId(long userId);

    /**
     * 定义验证登录的token
     *
     * @param accessToken
     * @return
     */
    boolean validateAccessToken(String accessToken);

    /**
     * 定义查找所有用户
     *
     * @return
     */
    List<User> findAllUser();

}
