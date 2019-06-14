package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct模式     队列与key绑定
 * @author fahomlee
 *
 */
@Configuration
public class SenderConfigDirect {
    
    @Bean
    public Queue queue() {
        return new Queue("queue"); //指定消息队列的bindingkey
    }
}
