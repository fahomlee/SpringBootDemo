package com.example.demo.service;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
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
    @Autowired
    private YamlMapFactoryBean yamlMapFactoryBean;
    @PostConstruct   //服务器加载时
    public void init() {
        log.info("*************初始化。。。。");
        User user=new User();
        user.setId("111");
        user.setDesc("222");
        redisTemplate.opsForHash().put("AAA::BBB:CCC:KEY","A",JSON.toJSONString(user));
    }
    
    
    /**
     * 获取百融的行业编码对照列表（配置文件参数列表）
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Map<String,String>> getBairongIndustry() {
        return (List<Map<String,String>>) yamlMapFactoryBean.getObject().get("bairongIndustry");
    }
}
