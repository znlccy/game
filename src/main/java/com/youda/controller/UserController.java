package com.youda.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youda.annotation.Authorization;
import com.youda.interceptor.ResponseStatusCode;
import com.youda.model.User;
import com.youda.service.UserService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现用户控制器
 */
@RestController
/*@RequestMapping(value = "/user")*/
@CrossOrigin(maxAge=3600,origins="*")
public class UserController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	/**
	 * 实现自动依赖注入用户服务
	 */
	@Autowired
	public UserService userService;
	
	@RequestMapping(value=ERROR_PATH)  
    public String handleError(){  
        return "error/404.html";  
    }  
	
	/**
	 * 实现通过用户Id来查找用户
	 * @param userId
	 * @return
	 */
	/*@RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)*/
	@Authorization
	@GetMapping(value = "/user")
	public User findByUserId(@PathVariable("userId") String userId) {
		return userService.getUserByUserId(userId);
	}
	
	/**
	 * 实现通过用户名来查找用户
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/getname", method = RequestMethod.GET)
	public User findByUserName(@RequestParam("userName") String userName) {
		return userService.findUserByUserName(userName);
	}
	
	/**
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/modifybyname", method = RequestMethod.PUT )
	public boolean modifyByUserName(@RequestParam String userName) {
		System.out.println(userService.findUserByUserName(userName));
		User user = userService.findUserByUserName(userName);
		user.setUserName("李小龙");
		userService.modifyByUserName(user);
		return false;
	}

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
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity userRegistered(@RequestBody Map map) {
		
		String userName = ((String) map.get("userName")).trim();
		String userPassword = ((String) map.get("userPassword")).trim();
		String userConfirmPassword = ((String) map.get("userConfirmPassword")).trim();
		
		return null;
	}
	
	/*2.实现后台数据登录的功能*/
	/**
	 * 实现后台加前台数据登录的功能,这点是经过加密之后生成的appkey
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
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
			User user = userService.findUserByUserName(userName);
			return ResponseStatusCode.putOrGetSuccess(user);
		}
	}
	
}
