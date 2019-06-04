package com.example.demo.service.impl;

import com.example.demo.dto.SysRoleDTO;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.service.ISysRoleService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.ExcelUtil;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author fahomelee
 * @since 2019-05-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public String importSysRole(List<SysRoleDTO> sysRoleDTOs) {
        List<SysRole> sysRoles = new ArrayList<>();
        // excel去重
        sysRoleDTOs = CommonUtil.rmDupAndKeepOrder(sysRoleDTOs);
        for (SysRoleDTO sysRoleDTO : sysRoleDTOs) {
            SysRole sysRole = new SysRole();
            BeanUtils.copyProperties(sysRoleDTO, sysRole);
            sysRoles.add(sysRole);
        }
        boolean b = saveBatch(sysRoles);
        if (!b) {
            return null;
        }
        return "success";
    }

    @Override
    public IPage<SysRole> listSysRole(Page<SysRole> page, SysRoleDTO sysRoleDTO) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
        if (StringUtils.isNotEmpty(sysRoleDTO.getName())) {
            queryWrapper.like("name", sysRoleDTO.getName());
        }
        if (StringUtils.isNotEmpty(sysRoleDTO.getSex())) {
            queryWrapper.eq("sex", sysRoleDTO.getSex());
        }
        return page(page, queryWrapper);
    }

    @Override
    public void exportSysRole(HttpServletResponse response) {
        List<SysUser> users = sysUserMapper.selectSysUserList();
        for (SysUser user : users) {
            List<SysRole> sysRoles = sysRoleMapper.selectUserRole(user.getId());
            user.setSysRoles(sysRoles);
        }
        ExcelUtil.exportExcel(users, "标题名称", "sheet名称", SysUser.class, "导出文件名称.xls", response);

    }

}
