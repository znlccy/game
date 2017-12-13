package com.youda.service;

import com.youda.request.api.ForgetFirstRequest;
import com.youda.request.api.ForgetSecondRequest;
import com.youda.request.api.LoginRequest;
import com.youda.request.api.RegisterRequest;
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

    /*定义新增用户统计*/
    ResponseEntity newUserStatistics();

    /*定义新增设备统计*/
    ResponseEntity newEquipmentStatistics();

    /*定义每天新增用户统计*/
    ResponseEntity everyDayNewUserStatistics();

    /*定义每周新增用户统计*/
    ResponseEntity everyWeekNewUserStatistics();

    /*定义每月新增用户统计*/
    ResponseEntity everyMonthNewUserStatistics();

    /*定义当日活跃用户统计*/
    ResponseEntity dengesActiveStatistics();

    /*定义当日付费用户统计*/
    ResponseEntity dengesPaymentStatistics();

    /*定义当日收入统计*/
    ResponseEntity dengesIncomeStatistics();

    /*定义用户留存统计*/
    ResponseEntity newUsersRetained();
}
