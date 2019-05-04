package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.SysUser;
import com.example.demo.service.SysUserService;

@RestController
@RequestMapping("/sysuser")
public class SysUserController {
	//调用同一个接口的不同实现类，实现类要定义@Service("name")
	@Resource(name="sysUserService")  
	private SysUserService sysUserService;
	@Resource(name="sysUserService1")   
	private SysUserService sysUserService1;
	
	@RequestMapping("/insertSysUserByService")
	public void insertSysUserByService() {
		SysUser sysUser=new SysUser();
		sysUser.setId("0001");
		sysUser.setName("我叫001");
		sysUser.setAge(18);
		sysUserService.insertSysUser(sysUser);
	}
	
	@RequestMapping("/insertSysUserByService1")
	public void insertSysUserByService1() {
		SysUser sysUser=new SysUser();
		sysUser.setId("0003");
		sysUser.setName("我叫003");
		sysUser.setAge(18);
		sysUserService1.insertSysUser(sysUser);
	} 
	
	/**
	 * 测试pagehelp插件
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listSysUser")
	//这样pageSize就可以为空了，此时int型的pageNum如果没有传参怎么会报错
	public List<SysUser> listSysUser(int pageNum,@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		List<SysUser> sysUserList=sysUserService.listSysUser(pageNum, pageSize);
		return sysUserList;
	} 
}
