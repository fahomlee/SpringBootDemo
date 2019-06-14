package com.example.demo.entity;

import lombok.Data;

@Data
// 使用EventListener监听不需要实现继承ApplicationEvent,spring 4.2开始可使用
public class UserEvent {

    private String id;
    private String desc;



}
