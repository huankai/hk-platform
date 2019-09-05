package com.hk.platform.commons.tree;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author kevin
 * @date 2019-8-10 14:17
 */
@Getter
@Setter
public abstract class BaseTreeNode<T extends BaseTreeNode> implements Serializable {

    /**
     * 名称
     */
    private String title;

    /**
     * id 值，唯一
     */
    private Serializable key;

    /**
     * 子节点
     */
    private List<T> children;
}
