package com.youda.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youda.annotation.Authorization;
import com.youda.interceptor.ResponseStatusCode;
import com.youda.model.User;
import com.youda.service.UserService;

import aj.org.objectweb.asm.Type;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现用户控制器
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(maxAge=3600,origins="*")
public class UserController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	/**
	 * 实现自动依赖注入用户服务
	 */
	@Autowired
	public UserService userService;

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return ERROR_PATH;
	}

	/*1.首先实现后台数据用户注册的功能*/
	/**
	 * 实现前台和后台数据注册的功能
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity userRegistered(@RequestBody Map map) {
		
		String userName = ((String) map.get("userName")).trim();
		String userPassword = ((String) map.get("userPassword")).trim();
		String userConfirmPassword = ((String) map.get("userConfirmPassword")).trim();

		System.err.println("用户名"+userName);
		
		return null;
	}
	
	/*2.实现后台数据登录的功能*/
	/**
	 * 实现后台加前台数据登录的功能,这点是经过加密之后生成的appkey
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	/*@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason= "Server Error")*/
	public ResponseEntity userLogin(@RequestParam String userName,String userPassword) {
		
		if(userName==null || userPassword==null)
		{
			return ResponseStatusCode.illegalError();
		} else if(userName.equals("") || "".equals(userPassword))
		{
			return ResponseStatusCode.nullPointerError();
		} 
		else
		{
			if(userService.login(userName, userPassword).getStatusCode().equals("404"))
			{
				return ResponseStatusCode.notFindError();
			} else if(userService.login(userName, userPassword).getStatusCode().equals("200"))
			{
				User user = userService.findUserByUserName(userName);
				return ResponseStatusCode.putOrGetSuccess(user);
			}
			return userService.login(userName, userPassword);
		}
	}

	/**
	 * 实现通过用户主键Id来更改用户信息
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
	public ResponseEntity userModify(@PathVariable("userId") long userId) {
		boolean result = userService.modifyByUserId(userId);
		return ResponseStatusCode.postSuccess(result);
	}
	
	/**
	 * 实现通过用户主键Id来删除用户
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{userId}",method = RequestMethod.DELETE)
	public ResponseEntity userDelete(@PathVariable("userId") long userId) {
		
		userService.deleteByUserId(userId);
			
		return ResponseStatusCode.deleteSuccess();
		
	}

	/**
	 * 通过用户主键来获取用户所有信息
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{userId}",method = RequestMethod.GET)
	public ResponseEntity findUserByUserId(@PathVariable("userId") long userId) {
		User user = userService.getUserByUserId(userId);
		return ResponseStatusCode.postSuccess(user);
	}

	/**
	 * 实现通过用户名来获取用户所有信息
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "userName",method = RequestMethod.GET)
	public ResponseEntity findUserByUserName(@Param("userName") String userName) {
		User user = userService.findUserByUserName(userName);
		return ResponseStatusCode.postSuccess(user);
	}
}
