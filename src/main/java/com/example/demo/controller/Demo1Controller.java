package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.conf.PropConfig;
import com.example.demo.pojo.User;

@Controller
@RequestMapping("/demo1")
public class Demo1Controller {
	//默然获取application.properties里的参数对应的值
	@Value("${spring.thymeleaf.prefix}")
	private String proValue;
	//加载读取配置文件的类
	@Autowired
	private PropConfig propConfig;
	
	@RequestMapping("/printStr")
	public String printStr() {
		return "hello springboot";
	}

	/**
	 * 返回thymeleaf模板
	 * 
	 * @param map
	 * @return 不能使用@RestController，不然会当作json处理，无法返回到具体页面
	 */
	@RequestMapping("/toThymelefPage")
	public String toThymelefPage(ModelMap map,HttpServletRequest request) {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setId("a");
		user.setName("张三");
		user.setAge(18);
		user.setDesc("<font color='green'>18岁是一朵花啊</font>");
		User user1 = new User();
		user1.setId("a");
		user1.setName("<font color='green'>李四<font color='green'>");
		user1.setAge(28);
		user1.setDesc("<font color='red'>28岁也是一朵花啊</font>");
		userList.add(user);
		userList.add(user1);
		// 字符串
		map.addAttribute("name", "一个帅哥");
		// 对象
		map.addAttribute("user", user);
		// 集合
		map.addAttribute("userList", userList);
		// 配置文件参数
		System.out.println("++resource.properties++"+propConfig.getA());
		// 配置文件参数集合
		for(String str:propConfig.getNames()) {
			System.out.println("--resource.properties--"+str);
		}
		// 配置文件参数
		map.addAttribute("confEle_a", propConfig.getA());
		return "index";
	}
	@RequestMapping("/getProConfElement")
	@ResponseBody
	public void getProConfElement(ModelMap map) {
		System.out.println();
	}
}
