package com.springaft.adminservice.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springaft.admin.api.entity.SysMenu;
import com.springaft.auth.api.vo.MenuVO;
import com.springaft.common.respbase.ResponseResult;

import java.util.List;

/**
 * 名称：菜单权限表 服务类<br>
 * 描述：菜单权限表 服务类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 通过角色编号查询URL 权限
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<MenuVO> getMenuByRoleId(Integer roleId);

    /**
     * 级联删除菜单
     *
     * @param id 菜单ID
     * @return 成功、失败
     */
    ResponseResult removeMenuById(Integer id);

    /**
     * 更新菜单信息
     *
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    Boolean updateMenuById(SysMenu sysMenu);
}
