package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


/**
 * 配置信息
 *
 * @author: kevin
 * @date: 2018-09-20 14:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(value = "sys_config")
public class SysConfig extends AbstractAuditable {

    /**
     * 续期类型
     */
    @Column(value = "key_")
    private String key;

    /**
     * 名称
     */
    @Column(value = "name_")
    private String name;

    /**
     * 值
     */
    @Column(value = "value_")
    private String value;

    /**
     * 值类型
     */
    @Column(value = "value_type")
    private Byte valueType;

    /**
     *
     */
    @Column(value = "description")
    private String description;


}
