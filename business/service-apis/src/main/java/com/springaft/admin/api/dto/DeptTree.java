package com.springaft.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 名称：部门树<br>
 * 描述：部门树<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
    private String name;
}
