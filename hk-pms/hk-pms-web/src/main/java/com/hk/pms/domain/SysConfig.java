package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 配置信息
 *
 * @author: kevin
 * @date: 2018-09-20 14:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_config")
public class SysConfig extends AbstractAuditable {

    /**
     * 续期类型
     */
    @Column(name = "key_", updatable = false)
    private String key;

    /**
     * 名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 值
     */
    @Column(name = "value_")
    private String value;

    /**
     * 值类型
     */
    @Column(name = "value_type")
    private Byte valueType;

    /**
     *
     */
    @Column(name = "description")
    private String description;


}
