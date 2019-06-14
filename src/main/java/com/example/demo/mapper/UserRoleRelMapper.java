
package com.example.demo.mapper;

import com.example.demo.entity.UserRoleRel;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户-角色关系表 Mapper 接口
 * </p>
 *
 * @author fahomelee
 * @since 2019-06-04
 */
@Mapper
public interface UserRoleRelMapper extends BaseMapper<UserRoleRel> {

}

