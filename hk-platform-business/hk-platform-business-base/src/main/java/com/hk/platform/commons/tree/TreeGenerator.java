package com.hk.platform.commons.tree;

import com.hk.commons.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @date 2018-08-29 08:55
 */
public interface TreeGenerator<T extends BaseTreeNode> {

    /**
     * 获取根节点
     *
     * @param params params
     * @return 根节点
     */
    List<T> rootNode(Map<String, Object> params);

    /**
     * 获取上级节点的子节点
     *
     * @param parentId 上级节点Id
     * @param params   参数
     * @return 子节点
     */
    List<T> childList(Serializable parentId, Map<String, Object> params);

    default boolean hasChild(Serializable parentId, Map<String, Object> params) {
        return CollectionUtils.isNotEmpty(childList(parentId, params));
    }

    /**
     * 当前节点是否为根节点
     *
     * @param t      当前节点
     * @param params 参数
     * @return true or false
     */
    default boolean isRootNode(T t, Map<String, Object> params) {
        return CollectionUtils.isEmpty(childList(t.getValue(), params));
    }
}
