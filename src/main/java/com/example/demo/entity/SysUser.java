package com.example.demo.entity;

import java.util.List;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.Data;

@Data
public class SysUser {
    /**
     * 主键
     */
    private String id;

    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 20,needMerge = true)
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    
    @ExcelCollection(name = "角色", orderNum = "4")
    private List<SysRole> sysRoles;
}