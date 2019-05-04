package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		Jedis jedis = new Jedis("127.0.0.1");
		jedis.set("BBB", "bbb");
		jedis.close();
	}
}
