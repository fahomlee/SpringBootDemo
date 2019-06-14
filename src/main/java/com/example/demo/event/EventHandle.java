package com.example.demo.event;

import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;
import com.example.demo.entity.UserEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 监听处理器
 * 
 * @author fahomlee
 *
 */
@Component
@Slf4j
//@EnableAsync // 开启监听支持异步，如果要配置异步监听时需要开启
public class EventHandle {

    @EventListener(classes = UserEvent.class) // spring4.2以后支持
    //@Async // 配置异步，根据需要配置
    public void listenUserEvent(UserEvent userEvent) {
        log.info("监听到事件，id=" + userEvent.getId() + "，desc=" + userEvent.getDesc());
    }
   
}
