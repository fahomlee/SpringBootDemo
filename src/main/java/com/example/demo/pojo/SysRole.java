package com.example.demo.pojo;

import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class SysRole {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @Size(max=50)
    @Excel(name="姓名",width= 20)
    @ApiModelProperty(name="姓名")
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 1男0女
     */
    @Size(max=1)
    @Excel(name="性别",width= 10,replace = {"男_1", "女_0"})
    @ApiModelProperty(name="性别")
    private Integer sex;


}
