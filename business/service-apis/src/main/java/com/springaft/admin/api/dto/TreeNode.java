package com.springaft.admin.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Data
public class TreeNode {
    protected int id;
    protected int parentId;
    protected List<TreeNode> children = new ArrayList<TreeNode>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
