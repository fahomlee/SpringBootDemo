package com.example.demo.service.provider;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.service.RemoteUserService;

/**
 * <p>
 * 用户-角色关系表 服务实现类
 * </p>
 *
 * @author fahomelee
 * @since 2019-06-04
 */
@Service(version = "1.0.0")//如果指定了版本号，消费者也需要配置版本号才可以成功调用@Reference(version = "1.0.0")
@Component
public class UserServiceImpl implements RemoteUserService {
    
    @Override
    public String getUserName() {
        return "调用dubbo";
    }

}
