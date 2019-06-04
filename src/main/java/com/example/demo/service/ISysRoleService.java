package com.example.demo.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.SysRoleDTO;
import com.example.demo.entity.SysRole;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author fahomelee
 * @since 2019-05-29
 */
public interface ISysRoleService extends IService<SysRole> {
    String importSysRole(List<SysRoleDTO> sysRoleDTOs);

    IPage<SysRole> listSysRole(Page<SysRole> page,SysRoleDTO sysRoleDTO);

    void exportSysRole(HttpServletResponse response);
}
