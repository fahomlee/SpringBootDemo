package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者端（接收MQ）
 * @author fahomlee
 *
 */
@Component
public class MQReceiver {

    /**
     * Direct
     */

    @RabbitListener(queues = "queue") // 监听器监听指定的Queue
    public void processDirect(String str) {
        System.out.println("Direct Receive:" + str);
    }


    /**
     * Topic
     */

    @RabbitListener(queues = "topic.message") // 监听器监听指定的Queue
    public void processTopic1(String str) {
        System.out.println("topic message:" + str);
    }

    @RabbitListener(queues = "topic.messages") // 监听器监听指定的Queue
    public void processTopic2(String str) {
        System.out.println("topic messages:" + str);
    }

    /**
     * Fanout
     */

    @RabbitListener(queues = "fanout.A") // 监听器监听指定的fanout.A
    public void processFanout1(String str) {
        System.out.println("fanout.A message:" + str);
    }

    @RabbitListener(queues = "fanout.B") // 监听器监听指定的Queue
    public void processFanout2(String str) {
        System.out.println("fanout.B messages:" + str);
    }

    @RabbitListener(queues = "fanout.C") // 监听器监听指定的Queue
    public void processFanout3(String str) {
        System.out.println("fanout.C messages:" + str);
    }
}
