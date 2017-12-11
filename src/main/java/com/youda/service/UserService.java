package com.youda.service;

import com.youda.request.ForgetFirstRequest;
import com.youda.request.ForgetSecondRequest;
import com.youda.request.LoginRequest;
import com.youda.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

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
    ResponseEntity forgotPasswordStart(ForgetFirstRequest request);

    /**
     * 定义忘记密码的第二步
     *
     * @return
     */
    ResponseEntity forgotPasswordEnd(ForgetSecondRequest request);

}
