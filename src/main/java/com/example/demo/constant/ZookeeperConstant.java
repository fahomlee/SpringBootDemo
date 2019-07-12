package com.example.demo.constant;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  常量
 **/
public interface ZookeeperConstant {

    /**
     * 审核校验天数
     */
    AtomicReference<Integer> AUDIT_VALID_DAY = new AtomicReference<>();
    /**
     * 回调URL
     */
    AtomicReference<String> CALLBACK_URL = new AtomicReference<>();
}
