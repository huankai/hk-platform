package com.hk.pms.commons.tree;

import com.hk.platform.commons.tree.TreeNode;
import lombok.Builder;
import lombok.Data;

/**
 * @author: kevin
 * @date 2018-08-29 09:08
 */
@Data
@Builder
public class ResourceTree extends TreeNode {

    private String resourceUri;

    private String icon;

    private String target;

    private Byte resourceType;
}
