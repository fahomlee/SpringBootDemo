package com.example.demo.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.example.demo.util.YamlPropertySourceFactory;

/**
 * 读取yml配置文件
 *需要定义YamlPropertySourceFactory
 * @author fahomlee
 * @date 2019/11/19
 */
@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:config/xxx.yml")
@Data
public class YmlConfig {

    /**
     * aaa
     */
    @Value("${aaa}")
    private String aaa;

}
