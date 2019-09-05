package com.hk.platform.commons.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kevin
 * @date 2019-9-5 20:08
 * @see https://vue.ant.design/components/tree-cn/
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AntDesignTreeNode extends BaseTreeNode<AntDesignTreeNode> {

    /**
     * 是否禁用
     */
    private boolean disabled;

    /**
     * 复选框是否禁用
     */
    private boolean disableCheckbox;

}
