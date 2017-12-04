package com.youda.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class TestRedis {
	
	/**
	 * 实现String Redis模板自动依赖注入
	 */
	/*@Autowired
	private StringRedisTemplate stringRedisTemplate;*/
	
	/**
	 * 实现Redis模板自动依赖注入
	 */
	/*@Autowired
	private RedisTemplate redisTemplate;*/
	
	/*@Test
	public void test() throws Exception {
		System.err.println(stringRedisTemplate);
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}*/
}
