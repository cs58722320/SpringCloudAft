package com.springaft.adminservice.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springaft.admin.api.dto.UserInfo;
import com.springaft.admin.api.entity.SysRole;
import com.springaft.admin.api.entity.SysUserEntity;
import com.springaft.adminservice.mapper.SysUserMapper;
import com.springaft.adminservice.service.*;
import com.springaft.auth.api.vo.MenuVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private final SysMenuService sysMenuService;
    private final SysRoleService sysRoleService;
    private final SysDeptService sysDeptService;
    private final SysUserRoleService sysUserRoleService;
    private final SysDeptRelationService sysDeptRelationService;

    /**
     * 通过查用户的全部信息
     *
     * @param sysUser 用户
     * @return
     */
    @Override
    public UserInfo getUserInfo(SysUserEntity sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表  （ID）
        List<Integer> roleIds = sysRoleService.listRolesByUserId(sysUser.getUserId())
                .stream()
                .map(SysRole::getRoleId)
                .collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));

        //设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.getMenuByRoleId(roleId)
                    .stream()
                    .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
                    .map(MenuVO::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

}
