package com.hk.emi.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: kevin
 * @date: 2017-11-29 17:11
 */
@Data
@Entity
@Table(name = "sys_child_code")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class ChildCode extends AbstractAuditable {

    @Column(name = "base_code_id")
    @NotEmpty
    private String baseCodeId;

    @Column(name = "child_code")
    @NotEmpty
    @Length(max = 20)
    private String childCode;

    @Column(name = "code_value")
    @NotNull
    private Byte codeValue;

    @Column(name = "code_name")
    @NotEmpty
    @Length(max = 20)
    private String codeName;

    /**
     * 只能是0和1两个值
     */
    @Column(name = "state")
    @NotNull
    @EnumByte(values = {0, 1})
    private Byte state;

    @Column(name = "description")
    private String description;


}
