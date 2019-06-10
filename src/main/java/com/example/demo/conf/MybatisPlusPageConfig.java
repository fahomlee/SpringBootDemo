package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * 开启MybatisPlus分页插件，支持IPage<Obj>返回
 * @author fahomlee
 *
 */
@Configuration
public class MybatisPlusPageConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
