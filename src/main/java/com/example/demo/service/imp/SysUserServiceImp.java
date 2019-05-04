package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.pojo.SysUser;
import com.example.demo.service.SysUserService;
import com.github.pagehelper.PageHelper;
@Service("sysUserService")//可以定义多个实现类
public class SysUserServiceImp implements SysUserService{
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	//事务隔离级别REQUIRED，有事务加入事务中，没有则新建事务    增删改
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertSysUser(SysUser sysUser) {
		System.out.println("执行sysUserService");
		sysUserMapper.insert(sysUser);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSysUser(SysUser sysUser) {
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		
	}

	@Override
	//事务隔离级别SUPPORTS，有事务加入事务中，没有则脱离事务执行    查
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> listSysUser(int pageNum, int pageSize) {
		System.out.println("测试PageHelper，pageNum大于等于最后一页都是显示结果都是最后一页的数据");
		PageHelper.startPage(pageNum,pageSize);
		List<SysUser> sysUserList=sysUserMapper.selectSysUserList();
		return sysUserList;
	}

}
