package com.youda.rediscache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-01
 * @introduce 实现Redis的缓存配置机制
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * 实现Redis缓存服务器地址的配置
	 */
	@Value("${spring.redis.host}")
	private String host;
	
	/**
	 * 实现Redis缓存服务器地址端口的配置
	 */
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	/**
	 * 缓存管理器
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
		// TODO Auto-generated method stub
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		//设置缓存过期时间
		cacheManager.setDefaultExpiration(10000);
		return cacheManager;
	}
	
	/**
	 * 缓存模板
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate(connectionFactory);
		
		return template;
	}
	
	setSerializer() {
		
	}
}
