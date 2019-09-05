package com.hk.platform.commons.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hk.commons.util.ObjectUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * 级联选择
 *
 * @author kevin
 * @date 2019-9-4 14:26
 */
@Getter
@Setter
@NoArgsConstructor
public class Cascader {

    private String value;

    private String label;

    @JsonIgnore
    private Byte level;

    /**
     * 注意，这里 要使用 装箱类型，在使用 antd 级联异步加载的时候需要此属性，
     * 如果使用 基本类型 会生成 isLeft() 方法，而使用 装箱类型 ，生成的 get 方法为 getIsLeft()
     */
    private Boolean isLeaf = false;

    public Cascader(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ChildCascader extends Cascader {

        private List<ChildCascader> children;

        public ChildCascader(String value, String label, List<ChildCascader> children) {
            super(value, label);
            this.children = children;
        }

        public List<ChildCascader> getChildren() {
            return ObjectUtils.defaultIfNull(children, Collections.emptyList());
        }
    }
}
