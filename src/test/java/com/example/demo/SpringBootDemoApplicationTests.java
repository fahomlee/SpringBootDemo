package com.example.demo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.entity.User;
import com.example.demo.entity.UserEvent;
import com.example.demo.service.InterfaceService;
import com.example.demo.task.TaskDemo1;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
        // 指定队列
        template.convertAndSend("queue", "hello,Direct rabbit~");
    }

    @Test
    public void testSenderTopic() {
        // 指定 exchange，指定队列
        template.convertAndSend("topicExchange", "topic.message", "hello,Topic message rabbit~");
    }

    @Test
    public void testSenderFanout() {
        // 指定 exchange
        template.convertAndSend("fanoutExchange", "", "hello,Fanout rabbit~");
    }

    @Test
    public void testForEach() {
        Map<User, String> map = Maps.newHashMap();
        User usera = new User();
        usera.setId("a");
        User userb = new User();
        userb.setId("b");
        map.put(usera, "aa");
        map.put(userb, "bb");
        // *****JDK1.8迭代map
        map.forEach((key, value) -> {
            System.out.println(key.getId() + "......" + value);
        });

        List<String> list = Lists.newArrayList();
        list.add("aaaa");
        list.add("aaaa");
        list.add("");
        list.add(null);
        // *****JDK1.8 Lambda表达式
        // 1去重 distinct  2过滤掉空字符和null filter  3转换为集合 collect
        list = list.stream().distinct().filter(a -> StringUtils.isNotBlank(a)).collect(Collectors.toList());
        
        // *****JDK1.8迭代List
        list.forEach(item -> {
            System.out.println(item + "....item..");
        });
        
        List<User> userList = Lists.newArrayList();
        usera.setAge(1);
        userb.setAge(2);
        userList.add(usera);
        userList.add(userb);
         // *****JDK1.8 Lambda表达式
        //求集合中所有user的年龄的和    User::getAge = User.getAge()
        int sum = userList.stream().mapToInt(User::getAge).sum();
        System.out.println("...sum..." + sum);
        
        InterfaceService interfaceService=new InterfaceService() {
            public  int getInterfaceService() {
                return 1;
            }
        };
    }
}
