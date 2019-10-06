package com.springaft.adminservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springaft.admin.api.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * 名称：用户角色表 Mapper 接口<br>
 * 描述：用户角色表 Mapper 接口<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     * @author 寻欢·李
     * @date 2017年12月7日 16:31:38
     */
    Boolean deleteByUserId(@Param("userId") Integer userId);
}
