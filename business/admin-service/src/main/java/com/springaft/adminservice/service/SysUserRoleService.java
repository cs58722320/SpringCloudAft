package com.springaft.adminservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springaft.admin.api.entity.SysUserRole;

/**
 * 名称：用户角色表 服务类<br>
 * 描述：用户角色表 服务类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     * @author 寻欢·李
     * @date 2017年12月7日 16:31:38
     */
    Boolean removeRoleByUserId(Integer userId);
}
