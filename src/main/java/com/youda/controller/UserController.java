package com.youda.controller;

import com.youda.encrypt.RSAEncryt;
import com.youda.interceptor.ResponseStatusCode;
import com.youda.model.User;
import com.youda.request.LoginRequest;
import com.youda.request.RegisterRequest;
import com.youda.service.MessageAuthCodeService;
import com.youda.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author Chencongye
 * @Date 2017/12/7 10:02
 * @Version 1.0.0
 * @Instructions 实现用户控制器功能
 */

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(maxAge = 3600, origins = "*")
@Scope(value = "singleton")
public class UserController implements ErrorController {

    /*实现rsa加密解密验签的过程*/
    @Autowired
    private RSAEncryt rsaEncryt;

    /*实现短信验证码服务类的自动依赖注入*/
    @Autowired
    private MessageAuthCodeService messageAuthCodeService;

    /*实现错误路径定义*/
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

    /*声明公钥变量*/
    private String publicKey;

    /*声明私钥变量*/
    private String privateKey;

    /*声明初始化加密和解密*/
    public void setUp() throws Exception {
        Map<String, Object> keyMap = rsaEncryt.initKey();

        publicKey = rsaEncryt.getPublicKey(keyMap);
        privateKey = rsaEncryt.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

	/*1.首先实现后台数据用户注册的功能*/

    /**
     * 实现前台和后台数据注册的功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity userRegistered(@RequestBody RegisterRequest request) {

        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        if (!messageAuthCodeService.isSuccess(request.getUserName(), request.getVerificationCode())) {
            return ResponseStatusCode.verifyError();
        }
        return userService.register(request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity userLogin(@RequestBody LoginRequest request) throws Exception {
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return userService.login(request);


    }

    /**
     * 实现通过用户主键Id来更改用户信息
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity userModify(@PathVariable("userId") long userId) {
        boolean result = userService.modifyByUserId(userId);
        return ResponseStatusCode.postSuccess(result);
    }

    /**
     * 实现通过用户主键Id来删除用户
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public ResponseEntity userDelete(@PathVariable("userId") long userId) {
        userService.deleteByUserId(userId);
        return ResponseStatusCode.deleteSuccess();

    }

    /**
     * 通过用户主键来获取用户所有信息
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public ResponseEntity findUserByUserId(@PathVariable("userId") long userId) {
        User user = userService.getUserByUserId(userId);
        return ResponseStatusCode.postSuccess(user);
    }

    /**
     * 实现通过用户名来获取用户所有信息
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "userName", method = RequestMethod.GET)
    public ResponseEntity findUserByUserName(@Param("userName") String userName) {
        User user = userService.findUserByUserName(userName);
        return ResponseStatusCode.postSuccess(user);
    }

}
