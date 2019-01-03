package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author kevin
 * @date 2018-08-28 16:35
 */
@Data
@Table(value = "sys_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysResource extends AbstractAuditable {

    static final String STATE_CODE_ID = "4028c081655a3a5a01655a3acd160001";

    @NotEmpty
    @Column(value = "parent_id")
    private String parentId;

    /**
     * appId
     */
    @NotEmpty
    @Column(value = "app_id")
    private String appId;

    /**
     * 菜单名称
     */
    @NotEmpty
    @Column(value = "resource_name")
    @Length(max = 30)
    private String resourceName;

    /**
     * uri
     */
    @NotEmpty
    @Column(value = "resource_uri")
    @Length(max = 20)
    private String resourceUri;

    /**
     * <p>
     * _self <br/>
     * _blank <br/>
     * _top <br/>
     * _parent <br/>
     * </p>
     */
    @NotEmpty
    @Length(max = 10)
    @Column(value = "target")
    private String target;

    /**
     * 排序
     */
    @NotNull
    @Column(value = "ordered")
    private Byte ordered;

    /**
     * 资源菜单类型
     */
    @EnumByte(values = {0})
    @Column(value = "resource_type")
    private Byte resourceType;

    /**
     * 状态
     */
    @Column(value = "state")
    @EnumDict(codeId = STATE_CODE_ID)
    private Byte state;

    /**
     * 图标
     */
    @Column(value = "icon")
    private String icon;

    /**
     * 备注
     */
    @Column(value = "remark")
    private String remark;

}
