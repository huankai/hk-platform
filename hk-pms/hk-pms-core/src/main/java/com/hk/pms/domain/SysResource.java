package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author kevin
 * @date 2018-08-28 16:35
 */
@Data
@Entity
@Table(name = "sys_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysResource extends AbstractSnowflakeAuditable {

    static final long STATE_CODE_ID = 1212121221L;

    @NotNull
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * appId
     */
    @NotNull
    @Column(name = "app_id")
    private Long appId;

    /**
     * 菜单名称
     */
    @NotEmpty
    @Length(max = 30)
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * uri
     */
    @NotEmpty
    @Length(max = 20)
    @Column(name = "resource_uri")
    private String resourceUri;

    /**
     * <pre>
     * _self
     * _blank
     * _top
     * _parent
     * </pre>
     */
    @NotEmpty
    @Length(max = 10)
    @Column(name = "target")
    private String target;

    /**
     * 排序
     */
    @NotNull
    @Column(name = "ordered")
    private Integer ordered;

    /**
     * 资源菜单类型
     */
    @EnumByte(values = {0})
    @Column(name = "resource_type")
    private Byte resourceType;

    /**
     * 状态
     */
    @Column(name = "state")
    @EnumDict(codeId = STATE_CODE_ID)
    private Byte state;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

}
