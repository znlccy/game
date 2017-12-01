package com.youda.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.youda.interceptor.AuthInterceptor;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 实现自己web应用的配置添加错误拦截器
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{
	
	/* 
	 * 添加自定义的拦截器
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub\
		// 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
		/*registry.addInterceptor()*/
		super.addInterceptors(registry);
	}

}
