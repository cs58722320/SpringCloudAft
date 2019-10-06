package com.springaft.adminservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springaft.admin.api.entity.SysMenu;
import com.springaft.admin.api.entity.SysRoleMenu;
import com.springaft.adminservice.mapper.SysMenuMapper;
import com.springaft.adminservice.mapper.SysRoleMenuMapper;
import com.springaft.adminservice.service.SysMenuService;
import com.springaft.auth.api.vo.MenuVO;
import com.springaft.common.constant.ResultConstant;
import com.springaft.common.respbase.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Cacheable(value = "menu_details", key = "#roleId  + '_menu'")
    public List<MenuVO> getMenuByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public ResponseResult removeMenuById(Integer id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query()
                .lambda().eq(SysMenu::getParentId, id));
        if (CollUtil.isNotEmpty(menuList)) {
            return ResponseResult.builder().msg("菜单含有下级不能删除").rtnCode(ResultConstant.HTTP_RES_CODE_500).build();
        }

        sysRoleMenuMapper
                .delete(Wrappers.<SysRoleMenu>query()
                        .lambda().eq(SysRoleMenu::getMenuId, id));

        //删除当前菜单及其子菜单
        return ResponseResult.builder().msg("菜单含有下级不能删除").rtnCode(ResultConstant.HTTP_RES_CODE_200).data(this.removeById(id)).build();
    }

    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }
}
