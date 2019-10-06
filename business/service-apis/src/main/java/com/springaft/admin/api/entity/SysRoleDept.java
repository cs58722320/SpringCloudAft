package com.springaft.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 名称：角色与部门对应关系<br>
 * 描述：角色与部门对应关系<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_dept")
public class SysRoleDept extends Model<SysRoleDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 部门ID
     */
    private Integer deptId;
}
