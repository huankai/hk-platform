package com.hk.platform.commons.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-29 08:50
 */
@Data
@SuppressWarnings("serial")
public class TreeNode implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 子节点
     */
    private List<? extends TreeNode> childList;
}
