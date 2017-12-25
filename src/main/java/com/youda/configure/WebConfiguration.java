package com.youda.configure;

import com.youda.interceptor.ChannelInterceptor;
import com.youda.interceptor.SignInterceptor;
import com.youda.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现自己web应用的配置添加错误拦截器
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private SignInterceptor signInterceptor;
    @Autowired
    private ChannelInterceptor channelInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(signInterceptor);
//        registry.addInterceptor(channelInterceptor);
        registry.addInterceptor(userInterceptor);
    }

}
