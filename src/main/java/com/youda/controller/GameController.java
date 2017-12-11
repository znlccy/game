package com.youda.controller;

import com.youda.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youda.interceptor.UserInterceptor;
import com.youda.service.GameService;


/**
 * @author chencongyeE
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义游戏控制器
 */

@RestController
@RequestMapping(value = "/game")
@CrossOrigin(maxAge = 3600, origins = "*")
public class GameController   {
	
	/**
	 * 实现游戏服务接口的自动依赖注入
	 */
	@Autowired
	private GameService gameService;

	/**
	 * 实现创建游戏的方法
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public boolean addGame() {

		return false;
	}
	
}
