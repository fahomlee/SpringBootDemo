package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *引入要使用的资源文件
 *@author lifahong
 *@date 2019/7/16
 */
@Configuration
@ImportResource(locations= {"classpath:config/dataconfig.xml"})
public class XMLConfig {

}
