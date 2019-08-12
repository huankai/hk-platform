package com.hk.platform.commons.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kevin
 * @date 2018-08-29 08:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class TreeNode<T> extends BaseTreeNode<T, Long> {

    private String name;

    private boolean open;

    private boolean checked;
}
