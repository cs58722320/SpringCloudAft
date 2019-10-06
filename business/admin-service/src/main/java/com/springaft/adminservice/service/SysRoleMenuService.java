package com.springaft.adminservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springaft.admin.api.entity.SysRoleMenu;

/**
 * 名称：角色菜单表 服务类<br>
 * 描述：角色菜单表 服务类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * 更新角色菜单
     *
     * @param role
     * @param roleId  角色
     * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
     * @return
     */
    Boolean saveRoleMenus(String role, Integer roleId, String menuIds);
}
