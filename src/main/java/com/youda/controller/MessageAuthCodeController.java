package com.youda.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youda.interceptor.AuthInterceptor;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义短信验证码控制器
 */

@RestController
@RequestMapping(value = "message")
@CrossOrigin(maxAge = 3600, origins="*")
public class MessageAuthCodeController extends AuthInterceptor{
	
	/**
	 * 实现发送短信验证码
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "send", method = RequestMethod.GET)
	public String sendMessageCode(@RequestParam String phone) {
		return "发送短信验证码成功";
	}
}
