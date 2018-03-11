package com.youda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @CreateTime:2018/3/11 13:07
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 邮件发送工具
 */

@Component
public class SendEmailUtils {

    /**
     * 注入javaMailSender
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userName;

    /**
     * @comment: sendNormalEmail 发送普通的邮件
     * @param: [title, titleWithName, content, contentWithName, email]
     * @return: void
     */
    private void sendNormalEmail(String title, boolean titleWithName,String content, boolean contentWithName, String email) {
        String dName = "YC-Play公司";
        MimeMessage mimeMessage = null;

        try {
            /*创建要发送的消息*/
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, null);
            /*设置谁发送的*/
            helper.setFrom(new InternetAddress(userName, dName, "UTF-8"));
            /*发送给谁[接受者邮箱]*/
            helper.setTo(email);
            /*标题内容*/
            title = titleWithName? title+ "-" + dName:title;
            /*发送邮件的辩题*/
            helper.setSubject(title);
            if (contentWithName) {
                content += "<p style='text-align:right'>" + dName + "</p>";
                content += "<p style='text-align:right'>" + NormalTools.curDate("yyyy-MM-dd HH:mm:ss") + "</p>";
            }
            /*发送的内容，是否为空*/
            helper.setText(content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

    /**
     * @comment: sendRegisterCode 发送注册时的验证码
     * @param: [email, code]
     * @return: void
     */
    public void sendRegisterCode(final String email, String code) {
        /*实例化一个StringBuffer实例*/
        final StringBuffer sb = new StringBuffer();
        sb.append("<h2>"+email+",您好!</h2>").append("<p style='color:red'>此次的验证码是:"+code+"</p>");
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("验证码",true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * @comment: sendRegisterSuccess 发送注册时的验证码
     * @param: [email, password, Url]
     * @return: void
     */
    public void sendRegisterSuccess(final String email, String password, String url) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<h3>恭喜您，注册成功!</h3>")
                .append("<h2>初始密码是:<b style='color:#F00'>").append(password).append("</b>,请不要告诉任何人!</h2>")
                .append("请及时<a href='").append(url).append("'>登陆网站</a>修改密码。");
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("注册成功",true, sb.toString(), true, email);
            }
        }).start();
    }

    /**
     * @comment: sendOnRegister 新用户注册通过
     * @param: [email, nickname, regEmail, url]
     * @return: void
     */
    public void sendOnRegister(final String email, String nickname, String regEmail, String url) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<a href='").append(url).append("'><h1>姓名：").append(nickname).append("</h1></a>");
        sb.append("<h3>注册邮箱：").append(regEmail).append("</h3>");
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendNormalEmail("新用户注册通知", true, sb.toString(), true, email);
            }
        }).start();
    }
}
