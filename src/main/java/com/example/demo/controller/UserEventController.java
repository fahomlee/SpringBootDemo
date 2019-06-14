package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.UserEvent;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件监听
 * 
 * @author fahomlee
 *
 */
@RestController
@RequestMapping("/userEvent")
@Api("UserEventController相关的api")
@Slf4j
public class UserEventController {
    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/createUserEvent")
    public UserEvent createUserEvent() {
        UserEvent userEvent = new UserEvent();
        userEvent.setId("xxx");
        // 监听器 com.example.demo.event.EventHandle
        log.info("发布事件");
        applicationContext.publishEvent(userEvent);// 发布事件
        log.info("发布事件完成");
        return userEvent;
    }

}
