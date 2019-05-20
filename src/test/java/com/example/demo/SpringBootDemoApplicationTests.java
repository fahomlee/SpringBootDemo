package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.task.TaskDemo1;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
    @Autowired
    private TaskDemo1 taskDemo1;// 注入要调试的类

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    /*
     * @Test public void testRedis() { Jedis jedis = new Jedis("127.0.0.1"); jedis.set("BBB", "bbb"); jedis.close(); }
     */

    /*
     * @Test public void testTask() { taskDemo1.reportCurrentTime(); }
     */
}
