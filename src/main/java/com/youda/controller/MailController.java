package com.youda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.youda.interceptor.UserInterceptor;

@RestController
@RequestMapping(value = "/mail")
@CrossOrigin(maxAge = 3600, origins = "*")
public class MailController extends UserInterceptor {

	/**
	 * 实现JavaMail自动依赖注入
	 */
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String username;
	
	/**
	 * 实现发送邮件功能
	 * @return
	 */
	/*@RequestMapping(value = "/send",method = RequestMethod.POST)
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
	}*/
	
	/**
	 * 发送简单电子邮件
	 */
	/*@RequestMapping(value = "/simple", method = RequestMethod.POST)
	public void SendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(username);
		message.setTo("756311868@qq.com");
		message.setSubject("标题:测试标题");
		message.setText("测试内容部分");
		mailSender.send(message);
	}*/
}
