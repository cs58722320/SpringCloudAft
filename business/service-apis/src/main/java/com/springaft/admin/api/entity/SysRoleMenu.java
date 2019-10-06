package com.springaft.admin.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 名称：角色菜单表<br>
 * 描述：角色菜单表<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单ID
     */
    private Integer menuId;
}
