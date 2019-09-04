package com.hk.platform.commons.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
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
}
