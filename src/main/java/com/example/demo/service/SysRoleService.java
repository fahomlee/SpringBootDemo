package com.example.demo.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.SysRoleDTO;
import com.example.demo.pojo.SysRole;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author fahomlee
 * @since 2019-05-21
 */
public interface SysRoleService extends IService<SysRole> {
    
   String importSysRole(List<SysRoleDTO> sysRoleDTOs);
 
}
