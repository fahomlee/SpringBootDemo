package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysRole;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author fahomelee
 * @since 2019-05-29
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectUserRole(@Param("id") String id);
}
