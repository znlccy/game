package com.youda.controller.api;

import com.youda.configure.EmailConfig;
import com.youda.service.UserService;
import com.youda.util.RandomTools;
import com.youda.util.SendEmailUtils;
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
}
