package com.youda;

import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义SpringBoot启动程序
 */

@SpringBootApplication
@MapperScan("com.youda.dao")
@EnableScheduling
@EnableCaching
public class GameApplication {

	/**
	 * 定义SpringBoot主程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}
	
	@RequestMapping(value = {"/",""})
	public String helloboot() {
		return "开启第一篇SpringBoot之旅!!";
	}
	
	/*@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {

	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

	            container.addErrorPages(error401Page, error404Page, error500Page);
	        }
	    };
	}*/
}
