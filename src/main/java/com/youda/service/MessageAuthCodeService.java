package com.youda.service;

import org.springframework.http.ResponseEntity;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义短信验证码接口
 */

public interface MessageAuthCodeService {

    /*定义通过手机号来查询短信验证码*/
    boolean isSuccess(String phone, String code);

    ResponseEntity saveCode(String phone, String code, String country);

    void delCodeByPhone(String phone);
}
