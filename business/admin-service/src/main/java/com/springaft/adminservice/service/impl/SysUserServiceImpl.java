package com.springaft.adminservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springaft.admin.api.dto.UserInfo;
import com.springaft.admin.api.entity.SysUserEntity;
import com.springaft.adminservice.mapper.SysUserMapper;
import com.springaft.adminservice.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
//        //设置角色列表  （ID）
//        List<Integer> roleIds = sysRoleService.listRolesByUserId(sysUser.getUserId())
//                .stream()
//                .map(SysRole::getRoleId)
//                .collect(Collectors.toList());
//        userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));
//
//        //设置权限列表（menu.permission）
//        Set<String> permissions = new HashSet<>();
//        roleIds.forEach(roleId -> {
//            List<String> permissionList = sysMenuService.getMenuByRoleId(roleId)
//                    .stream()
//                    .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
//                    .map(MenuVO::getPermission)
//                    .collect(Collectors.toList());
//            permissions.addAll(permissionList);
//        });
//        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

}
