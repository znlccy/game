package com.youda.util;

/*import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;*/

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-12-04
 * @introduce 测试连接Redis
 */

public class JRedisConnect {
	/*JedisPool pool;
	Jedis jedis;
	
	@Before
	public void setUP() {
		// TODO Auto-generated method stub
		pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
		jedis = pool.getResource();
	}*/
	
	/**
	 * 测试获取键的值
	 */
	/*@Test
	public void testGet() {
		System.out.println(jedis.get("lu"));
	}*/
	
	/** 
	* Redis存储初级的字符串 
	* CRUD 
	*/ 
	/*@Test 
	public void testBasicString(){ 
	//-----添加数据---------- 
		jedis.set("name","minxr");//向key-->name中放入了value-->minxr 
		System.out.println(jedis.get("name"));//执行结果：minxr 
		//-----修改数据----------- 
		//1、在原来基础上修改 
		jedis.append("name","jarorwar");   //很直观，类似map 将jarorwar append到已经有的value之后 
		System.out.println(jedis.get("name"));//执行结果:minxrjarorwar  
		//2、直接覆盖原来的数据 
		jedis.set("name","闵晓荣"); 
		System.out.println(jedis.get("name"));//执行结果：闵晓荣 
		//删除key对应的记录 
		jedis.del("name"); 
		System.out.println(jedis.get("name"));//执行结果：null     
		*//** 
		* mset相当于 
		* jedis.set("name","minxr"); 
		* jedis.set("jarorwar","闵晓荣"); 
		*//* 
		jedis.mset("name","minxr","jarorwar","闵晓荣");            
		System.out.println(jedis.mget("name","jarorwar"));  
	} */
	
	/** 
	* jedis操作Map 
	*/ 
	/*@Test 
	public void testMap(){ 
		Map<String,String> user=new HashMap<String,String>(); 
		user.put("name","minxr"); 
		user.put("pwd","password"); 
		jedis.hmset("user",user); 
		//取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List 
		//第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数 
		List<String> rsmap = jedis.hmget("user", "name"); 
		System.out.println(rsmap);  
		//删除map中的某个键值 
		//jedis.hdel("user","pwd"); 
		System.out.println(jedis.hmget("user", "pwd")); //因为删除了，所以返回的是null 
		System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数1 
		System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true 
		System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  [pwd, name] 
		System.out.println(jedis.hvals("user"));//返回map对象中的所有value  [minxr, password] 
		Iterator<String> iter=jedis.hkeys("user").iterator(); 
		while (iter.hasNext()){ 
			String key = iter.next();                
			System.out.println(key+":"+jedis.hmget("user",key)); 
		}     
	}*/ 
	/** 
	* jedis操作List 
	*/ 
	/*@Test 
	public void testList(){ 
		//开始前，先移除所有的内容 
		jedis.del("java framework"); 
		System.out.println(jedis.lrange("java framework",0,-1)); 
		//先向key java framework中存放三条数据 
		jedis.lpush("java framework","spring"); 
		jedis.lpush("java framework","struts"); 
		jedis.lpush("java framework","hibernate"); 
		//再取出所有数据jedis.lrange是按范围取出， 
		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有 
		System.out.println(jedis.lrange("java framework",0,-1)); 
	}*/ 
	
	/** 
	* jedis操作Set 
	*/ 
	/*@Test 
	public void testSet(){ 
		//添加 
		jedis.sadd("sname","minxr"); 
		jedis.sadd("sname","jarorwar"); 
		jedis.sadd("sname","闵晓荣"); 
		jedis.sadd("sanme","noname"); 
		//移除noname 
		jedis.srem("sname","noname"); 
		System.out.println(jedis.smembers("sname"));//获取所有加入的value 
		System.out.println(jedis.sismember("sname", "minxr"));//判断 minxr 是否是sname集合的元素 
		System.out.println(jedis.srandmember("sname")); 
		System.out.println(jedis.scard("sname"));//返回集合的元素个数 
	}*/  
	
}
