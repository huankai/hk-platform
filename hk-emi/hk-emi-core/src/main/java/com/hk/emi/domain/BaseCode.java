package com.hk.emi.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 字典
 *
 * @author kevin
 * @date 2017-11-29 16:27
 */
@Data
@Entity
@Table(name = "emi_base_code")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class BaseCode extends AbstractSnowflakeAuditable {

    /**
     *
     */
    @NotEmpty
    @Length(max = 20)
    @Column(name = "base_code")
    private String baseCode;

    /**
     *
     */
    @NotEmpty
    @Length(max = 20)
    @Column(name = "code_name")
    private String codeName;

    @Column(name = "is_gb")
    private Boolean isGb;

    /**
     *
     */
    @Column(name = "description")
    private String description;

}
