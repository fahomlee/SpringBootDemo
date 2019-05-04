package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.SysUser;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@RequestMapping("/insertRedis")
	public void insertRedis() {
		Jedis jedis = null;
		try {
			// jedis = new Jedis("127.0.0.1");
			// springboot启动已经连接到redis，不需要指定地址
			jedis = new Jedis();
			jedis.set("DDD", "ddd");
		} finally {
			jedis.close();
		}
	}

	@RequestMapping("/getRedis")
	public String getRedis() {
		Jedis jedis = null;
		try {
			// jedis = new Jedis("127.0.0.1");
			// springboot启动已经连接到redis，不需要指定地址
			jedis = new Jedis();
			String value = jedis.get("DDD");
			return value;
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	/**
	 * 存Json对象
	 */
	@RequestMapping("/insertRedisJson")
	public void insertRedisJson() {
		SysUser sysUser = new SysUser();
		sysUser.setId("0001");
		sysUser.setName("我叫001");
		sysUser.setAge(18);
		Jedis jedis = null;
		try {
			// jedis = new Jedis("127.0.0.1");
			// springboot启动已经连接到redis，不需要指定地址
			jedis = new Jedis();
			// 对象转json
			String sysUserJson = JSON.toJSONString(sysUser);
			jedis.set("json:sysuser", sysUserJson);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 取Json对象
	 */
	@RequestMapping("/getRedisJson")
	public String getRedisJson() {
		Jedis jedis = null;
		try {
			// jedis = new Jedis("127.0.0.1");
			// springboot启动已经连接到redis，不需要指定地址
			jedis = new Jedis();
			String value = jedis.get("json:sysuser");
			// json转对象
			SysUser sysUser = JSON.parseObject(value, SysUser.class);
			return sysUser.getName();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	
	/**
	 * 通过连接池操作redis
	 */
	@RequestMapping("/insertRedisByPool")
	public void insertRedisByPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制，
		// 如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted
		poolConfig.setMaxIdle(1000);
		poolConfig.setMaxIdle(32); // 设置剩余连接各数，如果小于这个就会抛异常
		// 表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛JedisConnectionException
		poolConfig.setMaxWaitMillis(100 * 1000);
		// 获得一个jedis实例的时候是否检查连接可用性(ping()),如果为true，则得到的jedis实例均是可用的
		poolConfig.setTestOnBorrow(true);
		// JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
		// springboot启动已经连接到redis，不需要指定地址
		JedisPool jedisPool = new JedisPool();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set("ZZZ", "zzz");
		} finally {
			if (jedis != null)
				jedis.close();
			if (jedisPool != null)
				jedisPool.close();
		}
	}

	@RequestMapping("/getRedisByPool")
	public String getRedisByPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；如果赋值为-1，则表示不限制，
		// 如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted
		poolConfig.setMaxIdle(1000);
		poolConfig.setMaxIdle(32); // 设置剩余连接各数，如果小于这个就会抛异常
		// 表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛JedisConnectionException
		poolConfig.setMaxWaitMillis(100 * 1000);
		// 获得一个jedis实例的时候是否检查连接可用性(ping()),如果为true，则得到的jedis实例均是可用的
		poolConfig.setTestOnBorrow(true);
		// JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
		// springboot启动已经连接到redis，不需要指定地址
		JedisPool jedisPool = new JedisPool();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get("ZZZ");
			return value;
		} finally {
			if (jedis != null)
				jedis.close();

			if (jedisPool != null)
				jedisPool.close();
		}
	}
}
