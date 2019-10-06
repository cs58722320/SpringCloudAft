package com.springaft.adminservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springaft.admin.api.entity.SysUserRole;
import com.springaft.adminservice.mapper.SysUserRoleMapper;
import com.springaft.adminservice.service.SysUserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 名称：用户角色表 服务实现类<br>
 * 描述：用户角色表 服务实现类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 根据用户Id删除该用户的角色关系
     *
     * @param userId 用户ID
     * @return boolean
     * @author 寻欢·李
     * @date 2017年12月7日 16:31:38
     */
    @Override
    public Boolean removeRoleByUserId(Integer userId) {
        return baseMapper.deleteByUserId(userId);
    }
}
