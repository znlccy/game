package com.youda.controller;

import com.youda.model.MessageAuthCode;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping(value = "getAll")
	public MessageAuthCode findAllMessageAuthCode() {
		return null;
	}
}
