package com.hk.emi.domain;

import com.hk.commons.validator.constraints.EnumByte;
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
 * @date 2017-11-29 17:11
 */
@Data
@SuppressWarnings("serial")
@Entity
@Table(name = "emi_child_code")
@EqualsAndHashCode(callSuper = true)
public class ChildCode extends AbstractSnowflakeAuditable {

    @Column(name = "base_code_id")
    @NotNull
    private Long baseCodeId;

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
    private Boolean state;

    /**
     * 是否为国标
     */
    @NotNull
    @Column(name = "is_gb")
    private Boolean isGb;

    @Column(name = "description")
    private String description;


}
