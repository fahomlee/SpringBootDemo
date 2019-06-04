package com.example.demo.service.impl;

import com.example.demo.entity.UserRoleRel;
import com.example.demo.mapper.UserRoleRelMapper;
import com.example.demo.service.IUserRoleRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关系表 服务实现类
 * </p>
 *
 * @author fahomelee
 * @since 2019-06-04
 */
@Service
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel> implements IUserRoleRelService {

}
