package com.example.demo.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//配置文件路径
@PropertySource("classpath:config/resource.properties")
//配置文件中参数的前缀    pom中不用引入spring-boot-configuration-processor也可以使用@ConfigurationProperties
@ConfigurationProperties(prefix = "com.demo")
/**
 * 读取配置文件（properties文件）
 * @author fahomlee
 *
 */
public class PropConfig {
	private String a;
	private String b;
	private String c;
	//读取配置文件中的集合
	private List<String> names = new ArrayList<>();

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
}
