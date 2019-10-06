package com.springaft.adminservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springaft.admin.api.entity.SysDept;
import com.springaft.admin.api.entity.SysDeptRelation;

/**
 * 名称：服务类<br>
 * 描述：服务类<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysDeptRelationService extends IService<SysDeptRelation> {

    /**
     * 新建部门关系
     *
     * @param sysDept 部门
     */
    void saveDeptRelation(SysDept sysDept);

    /**
     * 通过ID删除部门关系
     *
     * @param id
     */
    void removeDeptRelationById(Integer id);

    /**
     * 更新部门关系
     *
     * @param relation
     */
    void updateDeptRelation(SysDeptRelation relation);
}
