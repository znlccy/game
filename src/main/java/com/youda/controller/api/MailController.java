package com.youda.controller.api;

import com.youda.configure.EmailConfig;
import com.youda.model.User;
import com.youda.response.api.ReturnMessage;
import com.youda.service.UserService;
import com.youda.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @CreateTime:2018/3/11 11:35
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 邮箱控制器
 */

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(maxAge = 3600, origins = "*")
public class MailController {

    /**
     * 声明用户服务
     */
    @Autowired
    private UserService userService;

    /**
     * 声明发送邮件工具
     */
    @Autowired
    private SendEmailUtils sendEmailUtils;

    /**
     * 声明邮箱配置
     */
    @Autowired
    private EmailConfig emailConfig;

    /**
     * @comment: sendCode 发送验证码
     * @param: [email, request]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/mail/sendCode")   
    public String sendCode(@RequestParam(value = "email",defaultValue = "",required = true) String email, HttpServletRequest request) {

        /*创建随机验证码*/
        try {
            String code = RandomTools.randomCode();
            request.getSession().setAttribute("registerCode", code);
            /*发送邮件验证码*/
            sendEmailUtils.sendRegisterCode(email,code);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "1";
    }

    /**
     * @comment: register 用户注册
     * @param: [email, username, nickname, smscode, code, request]
     * @return: com.youda.response.api.ReturnMessage
     */
    /*@RequestMapping(value = "/mail/register")
    public ReturnMessage register(
            @RequestParam(value = "email",defaultValue = "",required = true) String email,
            @RequestParam(value = "userName",defaultValue = "",required = true) String username,
            @RequestParam(value = "nickname",defaultValue = "",required = false) String nickname,
            @RequestParam(value = "smscode",defaultValue = "",required = false) String smscode,
            @RequestParam(value = "code",defaultValue = "",required = true) String code,
            HttpServletRequest request) {

        ReturnMessage returnMessage = null;

        if (!StringUtils.isNotEmpty(email)) {
            return new ReturnMessage(ReturnMessage.ERROR,"邮箱不能为空!", null);
        }
        if (!StringUtils.isNotEmpty(username)) {
            return new ReturnMessage(ReturnMessage.ERROR, "用户名不能为空!",null);
        }
        if (userService.findByEmail(email)) {
            return new ReturnMessage(ReturnMessage.ERROR, "邮箱已存在!", null);
        }
        if (userService.findByUserName(username)) {
            return new ReturnMessage(ReturnMessage.ERROR, "用户名已经存在了!",null);
        }
        if (!StringUtils.isNotEmpty(code)) {
            return new ReturnMessage(ReturnMessage.ERROR, "验证码不能为空!", null);
        }

        String userPassword = RandomTools.randomCode();

        try {
            Member member=new Member();
            member.setEmail(email);
            member.setNickname(nickname);
            member.setState(StateConstant.USER_STATE_CHECK_ING.toString());
            member.setUsername(username);
            member.setPassword(SecurityUtil.md5(email,pwd));
            member=memberService.saveMember(member);
            if (member !=null){
                //注册成功后 通知注册人
                sendEmailUtils.sendRegisterSuc(email,pwd, emailConfig.URL+"?username="+username+"&code="+pwd);//注册成功后

                //注册成功后 通知管理员
                sendEmailUtils.sendOnRegister(emailConfig.MEMEMAIL,username,email,"#");//注册成功后
                return   new  ReturnMessages(RequestState.SUCCESS,"注册成功！",member);
            }else{
                return   new  ReturnMessages(RequestState.ERROR,"注册失败！",null);
            }
        } catch (Exception e) {
            return   new  ReturnMessages(RequestState.ERROR,"注册失败！",null);
        }
    }*/

    /**
     * 修改密码
     * @param code          初始密码
     * @param newPassword   新密码
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/updatePwd")
    public  ReturnMessages updatePwd(
            @RequestParam(value = "code",defaultValue = "",required = true) String code,
            @RequestParam(value = "username",defaultValue = "",required = true) String username,
            @RequestParam(value = "newPassword",defaultValue = "",required = true) String newPassword,
            HttpServletRequest request
    ){
        ReturnMessages returnMessages=null;
        if (!StringUtils.isNotEmpty(code)){
            return   new  ReturnMessages(RequestState.ERROR,"初始密码不能为空！",null);
        }
        if (!StringUtils.isNotEmpty(newPassword)){
            return   new  ReturnMessages(RequestState.ERROR,"新密码不能为空！",null);
        }
        Member member = memberService.findMemberByusername(username);
        if (member ==null){
            return   new  ReturnMessages(RequestState.ERROR,"该用户不存在！",null);
        }
        try {
            if ( StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(newPassword)){
                member.setPassword(SecurityUtil.md5(member.getEmail(),newPassword));
                member=memberService.updateMember(member);
                if (member !=null){
                    return   new  ReturnMessages(RequestState.SUCCESS,"修改密码成功！",member);
                }else{
                    return   new  ReturnMessages(RequestState.ERROR,"修改密码失败！",null);
                }
            }else{
                return   new  ReturnMessages(RequestState.ERROR,"修改密码失败！",null);
            }
        }catch (Exception e){
            return   new  ReturnMessages(RequestState.ERROR,"修改密码失败！",null);
        }
    }

    *//**
     * 找回密码
     * @param email     用户邮箱
     * @param request
     * @return
     *//*
    @RequestMapping(value ="/findPwd")
    public  ReturnMessages findPwd(
            @RequestParam(value = "email",defaultValue = "",required = true) String email,
            HttpServletRequest request
    ){
        ReturnMessages returnMessages=null;
        if (!StringUtils.isNotEmpty(email)){
            return   new  ReturnMessages(RequestState.ERROR,"邮箱不能为空！",null);
        }
        Member member= memberService.findMemeberByemail(email);
        if (member ==null){
            return   new  ReturnMessages(RequestState.ERROR,"该用户不存在！",null);
        }
        try {
            String url="http:192.168.0.17:8080/member/login";
            String randPwd = RandomTools.randomCode();//随机产生一个密码
            member.setPassword(SecurityUtil.md5(email,randPwd));
            member=memberService.updateMember(member);
            //发送邮件通知 用户
            sendEmailUtils.sendFindPwdSuc(email,randPwd,url);
            return   new  ReturnMessages(RequestState.SUCCESS,"找回密码成功！",member);
        }catch (Exception e){
            return   new  ReturnMessages(RequestState.ERROR,"找回密码失败！",null);
        }
    }

    *//**
     * 登录
     * @param email      邮箱
     * @param password   密码
     * @param request
     * @return
     *//*
    @RequestMapping(value = "/login")
    public  ReturnMessages login(
            @RequestParam(value = "email",defaultValue = "",required = true) String email,
            @RequestParam(value = "password",defaultValue = "",required = true) String password,
            HttpServletRequest request
    ){
        ReturnMessages returnMessages=null;
        try {
            if (!StringUtils.isNotEmpty(email)){
                return   new  ReturnMessages(RequestState.ERROR,"邮箱不能为空！",null);
            }
            if (!StringUtils.isNotEmpty(password)){
                return   new  ReturnMessages(RequestState.ERROR,"密码不能为空！",null);
            }
            Member member=memberService.findMemeberByemail(email);
            if (member== null){
                return   new  ReturnMessages(RequestState.ERROR,"用户不存在！",null);
            }
            String pwd=SecurityUtil.md5(email,password);
            if (member.getPassword().equals(pwd)){
                return   new  ReturnMessages(RequestState.SUCCESS,"登录成功！",null);
            }else{
                return   new  ReturnMessages(RequestState.ERROR,"密码错误,登录失败！",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return   new  ReturnMessages(RequestState.ERROR,"登录失败！",null);
        }
    }*/
}
