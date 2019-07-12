package com.example.demo.service;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化缓存到redis中
 * @author lifahong
 *
 */
@Component
@Slf4j
public class InitService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @PostConstruct   //服务器加载时
    public void init() {
        log.info("*************初始化。。。。");
        User user=new User();
        user.setId("111");
        user.setDesc("222");
        redisTemplate.opsForHash().put("AAA::BBB:CCC:KEY","A",JSON.toJSONString(user));
    }

}
