package com.youda.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youda.dao.UserMapper;
import com.youda.model.User;
import com.youda.service.UserService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现用户服务接口中的功能
 */

@Service
public class UserServiceImpl implements UserService {
	
	/**
	 * 定义日志打印输出变量
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * 实现UserMapper的自动依赖注入
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 实现RedisTemplate模板自动依赖注入
	 */
	@Autowired
	private RedisTemplate redisTemplate; 
	
	/* 
	 * 实现用户登录功能
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#login()
	 */
	@Override
	public ResponseEntity login(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 实现用户注册的功能
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#registered()
	 */
	@Override
	public ResponseEntity registered() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 实现忘记密码的第一步
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#forgotPasswordStart()
	 */
	@Override
	public ResponseEntity forgotPasswordStart() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 实现忘记密码的第二步
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#forgotPasswordEnd()
	 */
	@Override
	public ResponseEntity forgotPasswordEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 实现用户获取验证码
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#getVerificationCode()
	 */
	@Override
	public ResponseEntity getVerificationCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* 
	 * 实现用户主键Id来查询单个用户
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#getUserByUserId(java.lang.String)
	 */
	public User getUserByUserId(long userId) {
		//从缓存中获取用户信息
		String key = "user_"+userId;
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		
		//判断缓存是否存在
		boolean hasKey = redisTemplate.hasKey(key);
		
		//判断缓存是否存在，如果存在，直接从缓存中获取，如果不存在从数据库中查询
		if(hasKey)
		{
			//获取键对应的值
			User user = operations.get(key);
			//日志打印输出
			LOGGER.info("UserServiceImpl.getUserByUserId()：从缓存中获取用户信息>>"+user.toString());
			//返回数据
			return user;
		}
		else
		{
			//缓存不存在，从MySQL数据库中查询获取,也就是从DB中获取
			User user = userMapper.findByUserId(userId);
			//插入到缓存中
			operations.set(key, user, 10, TimeUnit.SECONDS);
			//返回数据
			return user;
		}
	}
	
	/* 
	 * 实现通过用户名称来获取单个用户
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#findUserByUserName(java.lang.String)
	 */
	@Override
	public User findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.findByUserName(userName);
	}

	/* 
	 * 实现通过用户Id来修改用户信息
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#modifyByUserId(java.lang.String)
	 */
	@Override
	public boolean modifyByUserId(long userId) {
		// TODO Auto-generated method stub
		User  user = userMapper.findByUserId(userId);
		System.err.println("user："+user);
		if(user==null)
		{
			return false;
		}
		else
		{
			user.setUserName("xiaolong");
			boolean result = userMapper.modifyUserInfo(user);
			if(result)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	/* 
	 * 实现通过用户名来修改用户信息
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#modifyByUserName(java.lang.String)
	 */
	@Override
	public boolean modifyByUserName(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	/* 
	 * 实现验证登录token的功能
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#validateAccessToken(java.lang.String)
	 */
	@Override
	public boolean validateAccessToken(String accessToken) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* 
	 * 实现查找所有用户的功能
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#findAllUser()
	 */
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userMapper.findAllUser();
	}
	
	
}
