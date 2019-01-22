package com.hk.emi.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * 字典
 *
 * @author kevin
 * @date 2017-11-29 16:27
 */
@Data
@Table(value = "emi_base_code")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class BaseCode extends AbstractAuditable {

    /**
     *
     */
    @Column(value = "base_code")
    @NotEmpty
    @Length(max = 20)
    private String baseCode;

    /**
     *
     */
    @Column(value = "code_name")
    @NotEmpty
    @Length(max = 20)
    private String codeName;

    /**
     *
     */
    @Column(value = "description")
    private String description;

}
