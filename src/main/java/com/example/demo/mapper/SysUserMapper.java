
package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author fahomelee
 * @since 2019-05-29
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> selectSysUserList();
}

