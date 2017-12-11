package com.youda.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.youda.interceptor.UserInterceptor;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义订单控制器
 */

@RestController
/*@RequestMapping(value = "/order")*/
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrderController   {
	
}
