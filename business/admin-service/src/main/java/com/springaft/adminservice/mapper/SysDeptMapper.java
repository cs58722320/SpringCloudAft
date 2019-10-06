package com.springaft.adminservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springaft.admin.api.entity.SysDept;

import java.util.List;

/**
 * 名称：部门管理 Mapper 接口<br>
 * 描述：部门管理 Mapper 接口<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 关联dept——relation
     *
     * @return 数据列表
     */
    List<SysDept> listDepts();
}
