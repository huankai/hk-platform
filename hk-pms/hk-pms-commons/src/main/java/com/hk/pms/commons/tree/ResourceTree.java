package com.hk.pms.commons.tree;

import com.hk.platform.commons.tree.TreeNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author kevin
 * @date 2018-08-29 09:08
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTree extends TreeNode<ResourceTree> {

    private String resourceUri;

    private String icon;

    private String target;

    private Byte resourceType;
}
