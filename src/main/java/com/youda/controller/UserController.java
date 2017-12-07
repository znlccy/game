package com.youda.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.youda.encrypt.DSAEncryt;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youda.interceptor.ResponseStatusCode;
import com.youda.model.User;
import com.youda.service.UserService;

import aj.org.objectweb.asm.Type;

/**
 * @Author Chencongye
 * @Date 2017/12/7 10:02
 * @Version 1.0.0
 * @Instructions 实现用户控制器功能
 */

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(maxAge=3600,origins="*")
@Scope(value = "singleton")
public class UserController implements ErrorController {

	@Autowired
	private DSAEncryt dsaEncryt;

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
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity userRegistered(@RequestBody Map map) {

		if (map.size()==0 || map.size()==1 || map.size()==2) {
			return ResponseStatusCode.illegalError();
		}
		else
		{
			/*接收用户名参数*/
			String userName = ((String) map.get("userName")).trim();

			/*接收用户密码参数*/
			String userPassword = ((String) map.get("userPassword")).trim();

			/*接收用户确认密码参数*/
			String verificationCode= ((String) map.get("verificationCode")).trim();

			/*判断用户名或者用户密码，用户确认密码是否为空*/
			if (userName==null || userPassword==null || verificationCode == null)
			{
				/*实现返回请求非法*/
				return ResponseStatusCode.illegalError();
			}
			else
			{
			/*判断用户名，用户密码，用户确认密码*/
				if(userName.equals("") || userPassword.equals("") || verificationCode.equals(""))
				{
					/*如果三个参数都为空返回为空指针异常*/
					return ResponseStatusCode.nullPointerError();
				}
				else if(!userPassword.equals(verificationCode))
				{
					return ResponseStatusCode.passwordsNoMatch();
				}
				else
				{
					if(userService.findUserByUserName(userName)==null)
					{
						User user = new User();
						user.setUserName(userName);
						user.setUserPassword(userPassword);
						user.setUserRegisteredTime(new Timestamp(System.currentTimeMillis()));
						userService.userRegist(user);
						User user_search = userService.findUserByUserName(user.getUserName());
						return ResponseStatusCode.putOrGetSuccess(user_search);
					}
					else
					{
						User user = userService.findUserByUserName(userName);
						return ResponseStatusCode.putOrGetSuccess(user);
					}
				}
			}
		}

	}
	
	/*2.实现后台数据登录的功能*/
	/**
	 * 实现后台加前台数据登录的功能,这点是经过加密之后生成的appkey
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	/*@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason= "Server Error")*/
	public ResponseEntity userLogin(@RequestParam String userName,String userPassword,@RequestHeader String sign) {

		if(userName==null && userPassword==null)
		{
			return ResponseStatusCode.illegalError();

		} else if(userName.equals("") || "".equals(userPassword))
		{
			return ResponseStatusCode.nullPointerError();
		} 
		else
		{
			if(userService.userLogin(userName, userPassword).getStatusCode().equals("404"))
			{
				return ResponseStatusCode.notFindError();
			} else if(userService.userLogin(userName, userPassword).getStatusCode().equals("200"))
			{
				User user = userService.findUserByUserName(userName);
				return ResponseStatusCode.putOrGetSuccess(user);
			}
			return userService.userLogin(userName, userPassword);
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
