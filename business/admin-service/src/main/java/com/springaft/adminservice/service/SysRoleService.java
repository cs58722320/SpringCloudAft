package com.springaft.adminservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springaft.admin.api.entity.SysRole;

import java.util.List;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);

    /**
     * 通过角色ID，删除角色
     *
     * @param id
     * @return
     */
    Boolean removeRoleById(Integer id);
}
