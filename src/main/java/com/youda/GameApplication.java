package com.youda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
	
	/*@Bean*/
}
