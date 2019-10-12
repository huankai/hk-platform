package com.hk.platform.commons.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author kevin
 * @date 2019-9-5 20:08
 * @see https://vue.ant.design/components/tree-cn/
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AntDesignTreeNode extends BaseTreeNode<AntDesignTreeNode> {

    /**
     * 是否禁用
     */
    private Boolean disabled = false;

    /**
     * 是否没有子级
     */
    private Boolean isLeaf = false;

    /**
     * 复选框是否禁用
     */
    private boolean disableCheckbox;

}
