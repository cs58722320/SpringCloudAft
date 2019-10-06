package com.springaft.adminservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springaft.admin.api.entity.SysDeptRelation;

/**
 * 名称：Mapper 接口<br>
 * 描述：Mapper 接口<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
    /**
     * 删除部门关系表数据
     *
     * @param id 部门ID
     */
    void deleteDeptRelationsById(Integer id);

    /**
     * 更改部分关系表数据
     *
     * @param deptRelation
     */
    void updateDeptRelations(SysDeptRelation deptRelation);
}
