package com.example.demo.service.impl;

import com.example.demo.pojo.SysRole;
import com.example.demo.dto.SysRoleDTO;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.service.SysRoleService;
import com.example.demo.util.CommonUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author fahomlee
 * @since 2019-05-21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public String importSysRole(List<SysRoleDTO> sysRoleDTOs) {
        sysRoleDTOs=CommonUtil.rmDupAndKeepOrder(sysRoleDTOs);
        List<SysRole> sysRoles=new ArrayList<>();
        for(SysRoleDTO sysRoleDTO:sysRoleDTOs) {
            SysRole sysRole=new SysRole();
            BeanUtils.copyProperties(sysRoleDTO, sysRole);
            sysRoles.add(sysRole);
        }
        boolean b=saveBatch(sysRoles);
        if(!b) {
            return "fail!!!";
        }
        return "success!!!";
    }

}
