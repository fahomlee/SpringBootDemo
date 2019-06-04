package com.example.demo.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

//@RestController
@RequestMapping("/sysuser")
//swagger访问地址 http://localhost:8080/swagger-ui.html
@Api("SysUserController相关的api")
public class SysUserController {
	//调用同一个接口的不同实现类，实现类要定义@Service("name")
	@Resource(name="sysUserService")  
	private SysUserService sysUserService;
	@Resource(name="sysUserService1")   
	private SysUserService sysUserService1;
	@ApiOperation(value="新增用户信息", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "SysUser", value = "用户详细实体SysUser", required = true, dataType = "SysUser")
	@RequestMapping(value="/insertSysUserByService",method = RequestMethod.POST)
	public void insertSysUserByService() {
		SysUser sysUser=new SysUser();
		sysUser.setId("0001");
		sysUser.setName("我叫001");
		sysUser.setAge(18);
		sysUserService.insertSysUser(sysUser);
	}
	@ApiOperation(value="新增用户信息2", notes="根据User对象创建用户2")
	@ApiImplicitParam(name = "SysUser", value = "用户详细实体SysUser2", required = true, dataType = "SysUser")
	@RequestMapping(value="/insertSysUserByService1",method = RequestMethod.POST)
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
	@ApiOperation(value="查询用户列表", notes="获取用户列表")
	@RequestMapping(value="/listSysUser",method = RequestMethod.GET)
	//这样pageSize就可以为空了，此时int型的pageNum如果没有传参怎么会报错
	public List<SysUser> listSysUser(int pageNum,@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		List<SysUser> sysUserList=sysUserService.listSysUser(pageNum, pageSize);
		return sysUserList;
	} 
}
