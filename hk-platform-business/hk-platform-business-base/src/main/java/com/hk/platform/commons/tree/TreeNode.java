package com.hk.platform.commons.tree;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author kevin
 * @date 2018-08-29 08:50
 */
@Data
@SuppressWarnings("serial")
public abstract class TreeNode<T> implements Serializable {

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
    private List<T> childs;
}
