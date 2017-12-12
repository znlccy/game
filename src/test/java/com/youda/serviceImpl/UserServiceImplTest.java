package com.youda.serviceImpl;

import com.youda.dao.UserMapper;
import com.youda.model.Token;
import com.youda.model.User;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 12, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserMapper userMapper;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: login(LoginRequest loginRequest)
    *
    */
    @Test
    public void testLogin() throws Exception {
    //TODO: Test goes here...
       User user =  userMapper.findByUserId(1);
       System.err.println("用户"+user.getUserName());
    }

    /**
    *
    * Method: register(RegisterRequest register)
    *
    */
    @Test
    public void testRegister() throws Exception {
    //TODO: Test goes here...
        System.err.println("用户操作层"+userMapper);
        User user = new User();
        user.setUserName("15900785383");
        user.setUserPassword("123456");
        user.setUserRegisteredTime(new Timestamp(System.currentTimeMillis()));
        Set<Token> tokens = new HashSet<Token>();
        Token token = new Token();
        token.setAccessToken("wejriejrtjeiwrtewrt");
        token.setExpirationTime(new Timestamp(System.currentTimeMillis()));
        tokens.add(token);
        user.setTokens(tokens);
        userMapper.addUser(user);
    }

    /**
    *
    * Method: forgotPasswordStart(ForgetFirstRequest request)
    *
    */
    @Test
    public void testForgotPasswordStart() throws Exception {
    //TODO: Test goes here...
        User user = userMapper.findByUserId(1);
        user.setUserPassword("123456789");
        user.setUserModifyTime(new Timestamp(System.currentTimeMillis()));
        userMapper.modifyUserInfo(user);
    }

    /**
    *
    * Method: forgotPasswordEnd(ForgetSecondRequest request)
    *
    */
    @Test
    public void testForgotPasswordEnd() throws Exception {
    //TODO: Test goes here...
    }


} 
