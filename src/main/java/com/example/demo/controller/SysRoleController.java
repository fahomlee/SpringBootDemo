
package com.example.demo.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.SysRoleDTO;
import com.example.demo.entity.SysRole;
import com.example.demo.service.ISysRoleService;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author fahomlee
 * @since 2019-05-21
 */

@RestController

@RequestMapping("/sysRole")

@Api("SysRoleController相关的api")

@Slf4j
public class SysRoleController {


    @Autowired
    private ISysRoleService iSysRoleService;


    @PostMapping("/addSysRole")

    @ApiOperation(value = "新增sysrole", notes = "新增sysrole")

    @ApiImplicitParam(name = "sysRoleDTO", value = "单个用户信息", dataType = "SysRoleDTO")
    public void addSysRole(@Valid @RequestBody SysRoleDTO sysRoleDTO) {
        log.info("add sysrole begin...");
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO, sysRole);
        iSysRoleService.save(sysRole);
    }


    @PostMapping("/updateSysRole")

    @ApiOperation(value = "修改sysrole", notes = "修改sysrole")

    @ApiImplicitParam(name = "sysRoleDTO", value = "单个用户信息", dataType = "SysRoleDTO")
    public void updateSysRole(@Valid @RequestBody SysRoleDTO sysRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO, sysRole);
        log.info("update sysrole begin...");
        iSysRoleService.updateById(sysRole);
    }


    @GetMapping("/listSysRole") // 不能使用@RequestBody

    @ApiOperation(value = "查询SysRole列表", notes = "查询SysRole列表")

    @ApiImplicitParams({@ApiImplicitParam(name = "sysRoleDTO", value = "单个用户信息", dataType = "SysRoleDTO"),

            @ApiImplicitParam(name = "page", value = "分页信息", dataType = "Page<SysRole>")})
    public IPage<SysRole> listSysRole(SysRoleDTO sysRoleDTO, Page<SysRole> page) {
        log.info("list sysrole begin...");
        return iSysRoleService.listSysRole(page, sysRoleDTO);
    }


    @PostMapping("/deleteSysRole")

    @ApiOperation(value = "删除sysrole", notes = "删除sysrole")

    @ApiImplicitParam(name = "sysRoleDTO", value = "单个用户信息", dataType = "SysRoleDTO")
    public void deleteSysRole(@RequestBody SysRoleDTO sysRoleDTO) {
        log.info("add sysrole begin...");
        List<String> idList = new ArrayList<String>();
        for (String id : sysRoleDTO.getIds()) {
            idList.add(id);
        }
        iSysRoleService.removeByIds(idList);
    }


    /**
     * 通过easypoi实现导入
     * 
     * @param file
     * @return
     */
    @PostMapping("/importSysRole")

    @ApiOperation(value = "导入sysrole", notes = "导入sysrole")
    public String importSysRole(@RequestParam("file") MultipartFile file) {
        log.info("import sysrole begin...");
        ImportParams importParams = new ImportParams(); // 默认排除第1行标题行，除标题行的第一行开始读取 importParams.setStartRows(0); // 需要验证
        importParams.setNeedVerfiy(true);
        try {
            ExcelImportResult<SysRoleDTO> excelImportResult =
                            ExcelImportUtil.importExcelMore(file.getInputStream(), SysRoleDTO.class, importParams);
            List<SysRoleDTO> sysRoleDTOs = excelImportResult.getList();
            List<SysRoleDTO> sysRoleDTOFails = excelImportResult.getFailList();
            if (sysRoleDTOs.size() == 0 && sysRoleDTOFails.size() == 0) {
                return "模板不能为空";
            }
            String b = iSysRoleService.importSysRole(sysRoleDTOs);
            return b;
        } catch (IOException e) {
            return "文件转换失败";
        } catch (Exception e) {
            log.error("import fail ...." + e.getMessage());
            return "模板错误";
        }
    }

    @GetMapping("/exportSysRole")
    @ApiOperation(value = "导出SysRole", notes = "导出SysUserAndRole")
    public void exportSysRole(HttpServletResponse response) {
        log.info("export sysrole begin...");
        iSysRoleService.exportSysRole(response);
    }


}


