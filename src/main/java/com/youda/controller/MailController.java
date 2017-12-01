package com.youda.controller;

import javax.mail.internet.MimeMessage;

import com.youda.interceptor.ResponseResult;
import com.youda.interceptor.ResponseStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import com.youda.interceptor.AuthInterceptor;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin(maxAge = 3600, origins = "*")
public class MailController extends AuthInterceptor {

	/**
	 * 实现JavaMail自动依赖注入
	 */
	@Autowired
	JavaMailSender mailSender;
	
	/**
	 * 实现发送邮件功能
	 * @return
	 */
	@RequestMapping(value = "/send",method = RequestMethod.POST)
	public ResponseEntity sendEmail() {
		try  
        {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom("18237177660@163.com");
            message.setTo("756311868@qq.com");
            message.setSubject("测试邮件主题");  
            message.setText("测试邮件内容");
            this.mailSender.send(mimeMessage);  

            return ResponseStatusCode.postSuccess(mimeMessage);
        }  
        catch(Exception ex) {
			return ResponseStatusCode.nullPointerError();
		}
	}
}
