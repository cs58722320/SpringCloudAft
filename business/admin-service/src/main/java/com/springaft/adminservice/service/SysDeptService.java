package com.springaft.adminservice.service;

import com.springaft.admin.api.dto.DeptTree;
import com.springaft.admin.api.entity.SysDept;

import java.util.List;

/**
 * 名称：部门管理 服务类<br>
 * 描述：部门管理 服务类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysDeptService {

    /**
     * 查询部门树菜单
     *
     * @return 树
     */
    List<DeptTree> listDeptTrees();

    /**
     * 查询用户部门树
     *
     * @return
     */
    List<DeptTree> listCurrentUserDeptTrees();

    /**
     * 添加信息部门
     *
     * @param sysDept
     * @return
     */
    Boolean saveDept(SysDept sysDept);

    /**
     * 删除部门
     *
     * @param id 部门 ID
     * @return 成功、失败
     */
    Boolean removeDeptById(Integer id);

    /**
     * 更新部门
     *
     * @param sysDept 部门信息
     * @return 成功、失败
     */
    Boolean updateDeptById(SysDept sysDept);
}
