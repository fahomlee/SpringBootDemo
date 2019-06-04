package com.example.demo.dto;

import java.io.Serializable;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author fahomlee
 * @since 2019-05-21
 */
@Data
@Accessors(chain = true)
public class SysRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String [] ids;

    @ApiModelProperty(name = "主键")
    @Positive
    private Integer id;
    
    /**
     * 名称
     */
    @Size(max = 50)
    @Excel(name = "姓名", width = 20,isImportField = "true")
    @ApiModelProperty(name = "姓名")
    private String name;



    /**
     * 1男0女
     */
    @Size(max = 1)
    @Excel(name = "性别", width = 10, replace = {"男_1", "女_0"},isImportField = "true")
    @ApiModelProperty(name = "性别")
    private String sex;


}
