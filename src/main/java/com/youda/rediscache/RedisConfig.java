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
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

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
		/*设置序列化工具*/
		setSerializer(template);
		/*之后设置属性*/
		template.afterPropertiesSet();
		return template;
	}
	
	/**
	 * 设置序列化工具
	 * @param template
	 */
	public void setSerializer(StringRedisTemplate template) {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		/*创建对象映射关联*/
		ObjectMapper om = new ObjectMapper();
		/*设置可视化*/
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		/*设置默认类型*/
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		/*设置对象关联*/
		jackson2JsonRedisSerializer.setObjectMapper(om);
		/*设置值得序列化*/
		template.setValueSerializer(jackson2JsonRedisSerializer);
	}
}
