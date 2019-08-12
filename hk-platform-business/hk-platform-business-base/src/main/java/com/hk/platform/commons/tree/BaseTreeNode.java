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
public abstract class BaseTreeNode<E, ID extends Serializable> implements Serializable {

    private ID id;

    private ID parentId;

    private List<E> childs;
}
