package com.springaft.adminservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springaft.admin.api.dto.UserInfo;
import com.springaft.admin.api.entity.SysUserEntity;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 查询用户信息
     *
     * @param sysUser 用户
     * @return userInfo
     */
    UserInfo getUserInfo(SysUserEntity sysUser);
}
