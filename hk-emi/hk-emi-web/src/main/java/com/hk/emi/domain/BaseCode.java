package com.hk.emi.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 字典
 *
 * @author: kevin
 * @date 2017-11-29 16:27
 */
@Data
@Entity
@Table(name = "sys_base_code")
@EqualsAndHashCode(callSuper = true)
public class BaseCode extends AbstractAuditable {

    /**
     *
     */
    @Column(name = "base_code")
    @NotBlank(message = "")
    @Length(max = 20, message = "")
    private String baseCode;

    /**
     *
     */
    @Column(name = "code_name")
    @NotBlank(message = "")
    @Length(max = 20, message = "")
    private String codeName;

    /**
     *
     */
    @Column(name = "description")
    private String description;

}
