package com.springaft.adminservice.mapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.springaft.admin.api.entity.SysMenu;
import com.springaft.admin.api.entity.SysRoleMenu;
import com.springaft.auth.api.vo.MenuVO;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 名称：菜单权限表 服务实现类<br>
 * 描述：菜单权限表 服务实现类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

        /**
         * 通过角色编号查询菜单
         *
         * @param roleId 角色ID
         * @return
         */
        List<MenuVO> listMenusByRoleId(Integer roleId);

        /**
         * 通过角色ID查询权限
         *
         * @param roleIds Ids
         * @return
         */
        List<String> listPermissionsByRoleIds(String roleIds);
}
