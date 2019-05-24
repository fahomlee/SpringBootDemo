package com.example.demo.controller;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.dto.SysRoleDTO;
import com.example.demo.pojo.SysRole;
import com.example.demo.service.SysRoleService;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/importSysRole")
    @ApiOperation(value = "导入sysrole", notes = "导入sysrole")
    public String importSysRole(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 默认排除第1行标题行，除标题行的第一行开始读取
        importParams.setStartRows(0);
        // 需要验证
        importParams.setNeedVerfiy(true);
        try {
            ExcelImportResult<SysRoleDTO> excelImportResult =
                            ExcelImportUtil.importExcelMore(file.getInputStream(), SysRoleDTO.class, importParams);
            List<SysRoleDTO> sysRoleDTOs = excelImportResult.getList();
            String b = sysRoleService.importSysRole(sysRoleDTOs);
            return b;
        } catch (IOException e) {
            return "文件转换失败";
        } catch (Exception e) {
            return "模板错误";
        }
    }

}

