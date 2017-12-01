package com.youda.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public UserMapper userMapper;
	
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
	public User getUserByUserId(String userId) {
		return userMapper.findByUserId(userId);
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
	public boolean modifyByUserId(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByUserId(user);
	}
	
	/* 
	 * 实现通过用户名来修改用户信息
	 * (non-Javadoc)
	 * @see com.youda.service.UserService#modifyByUserName(java.lang.String)
	 */
	@Override
	public boolean modifyByUserName(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByUserName(user);
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
