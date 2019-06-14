package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.entity.UserEvent;
import com.example.demo.task.TaskDemo1;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
    @Autowired
    private TaskDemo1 taskDemo1;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AmqpTemplate template;



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

    @Test
    public void testTask() {
        taskDemo1.reportCurrentTime();
    }

    /**
     * 事件监听测试
     */

    @Test
    public void testListenEvent() {
        UserEvent userEvent = new UserEvent();
        userEvent.setId("aaa");
        applicationContext.publishEvent(userEvent);// 发布事件
    }

    /**
     * MQ测试 com.example.demo.rabbitmq.MQReceiver
     */

    @Test
    public void testSenderDirect() {
        template.convertAndSend("queue", "hello,Direct rabbit~");
    }

    @Test
    public void testSenderTopic() {
        template.convertAndSend("topicExchange", "topic.message", "hello,Topic message rabbit~");
    }

    @Test
    public void testSenderFanout() {
        template.convertAndSend("fanoutExchange", "", "hello,Fanout rabbit~");
    }


}
