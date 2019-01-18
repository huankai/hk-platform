package com.hk.emi.domain;

import com.hk.commons.validator.constraints.EnumByte;
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
 * @date 2017-11-29 17:11
 */
@Data
@Table(value = "emi_child_code")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class ChildCode extends AbstractAuditable {

    @Column(value = "base_code_id")
    @NotEmpty
    private String baseCodeId;

    @Column(value = "child_code")
    @NotEmpty
    @Length(max = 20)
    private String childCode;

    @Column(value = "code_value")
    @NotNull
    private Byte codeValue;

    @Column(value = "code_name")
    @NotEmpty
    @Length(max = 20)
    private String codeName;

    /**
     * 只能是0和1两个值
     */
    @Column(value = "state")
    @NotNull
    @EnumByte(values = {0, 1})
    private Byte state;

    @Column(value = "description")
    private String description;


}
