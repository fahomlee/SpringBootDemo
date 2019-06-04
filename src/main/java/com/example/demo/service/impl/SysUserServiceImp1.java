package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;

@Service("sysUserService1")//可以定义多个实现类
public class SysUserServiceImp1 implements SysUserService {
	//@Autowired
	//private SysUserMapper sysUserMapper;

	@Override
	public void insertSysUser(SysUser sysUser) {
		System.out.println("执行sysUserService1");
		//sysUserMapper.insert(sysUser);
	}

	@Override
	public void updateSysUser(SysUser sysUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SysUser> listSysUser(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<SysUser> listSysUser() {
        // TODO Auto-generated method stub
        return null;
    }

}
