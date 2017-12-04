package com.youda.util;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-04
 * @introduce 测试连接Redis
 */

public class JRedisConnect {
	JedisPool pool;
	Jedis jedis;
	
	@Before
	public void setUP() {
		// TODO Auto-generated method stub
		pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
		jedis = pool.getResource();
	}
	
	/**
	 * 测试获取键的值
	 */
	@Test
	public void testGet() {
		System.out.println(jedis.get("lu"));
	}
	
	/**
	 * 测试基本字符操作
	 */
	@Test
	public void testBasicString() {
		/*添加数据*/
		jedis.set("name", "test"); //向key-->name中放入了value-->test
		System.out.println(jedis.get("name")); //执行结果是test
		
		/*修改数据*/
		jedis.append("name", "content"); //很直观,类似map将content
		
	}
}
