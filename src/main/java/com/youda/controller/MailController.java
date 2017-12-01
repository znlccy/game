package com.youda.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Object sendEmail() {
		try  
        {  
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom("****@126.com");
            message.setTo("****@example.com");  
            message.setSubject("测试邮件主题");  
            message.setText("测试邮件内容");
            this.mailSender.send(mimeMessage);  
            
            /*//*WebResult resultMsg = new WebResult(ResultStatusCode.OK.getErrcode(),
                    ResultStatusCode.OK.getErrmsg(), null);  
            return resultMsg; */
        }  
        catch(Exception ex)
        {  
            /*ResultMsg resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(),
                    ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);  
            return resultMsg;*/
        }
		return null;
	}
}
