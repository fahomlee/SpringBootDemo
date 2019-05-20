package com.example.demo.pojo;

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
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}