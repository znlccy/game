package com.youda.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @CreateTime:2018/3/11 11:37
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 邮件配置类
 */

@Configuration
public class EmailConfig {

    @Value("http://localhost:8080/member/updatePwd")
    public String URL;

    @Value("2111@qq.com")
    public String MEMEMAIL;

}
