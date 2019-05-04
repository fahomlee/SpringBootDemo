package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.SysUser;
/**
 * 处理sysuser信息的service
 * @author fahomlee
 *
 */
public interface SysUserService {

	void insertSysUser(SysUser sysUser);

	void updateSysUser(SysUser sysUser);

	List<SysUser> listSysUser(int pageNum, int pageSize);
}
