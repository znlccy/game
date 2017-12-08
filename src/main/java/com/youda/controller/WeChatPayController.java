package com.youda.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.youda.interceptor.UserInterceptor;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义微信支付的控制器
 */

@RestController
@RequestMapping(value = "wechat")
@CrossOrigin(maxAge = 3600, origins = "*")
public class WeChatPayController extends UserInterceptor {
	
	@RequestMapping(value = "apppay", method = RequestMethod.POST)
	public String WeChatAppPay() {
			
		return "nihao";
	}
}
