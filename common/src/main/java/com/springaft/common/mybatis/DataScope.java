package com.springaft.common.mybatis;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

/**
 * 名称：数据权限查询参数<br>
 * 描述：数据权限查询参数<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {
    /**
     * 限制范围的字段名称
     */
    private String scopeName = "deptId";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds;

    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;
}

