package com.hk.emi.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: kevin
 * @date 2017-11-29 17:11
 */
@Data
@Entity
@Table(name = "sys_child_code")
@EqualsAndHashCode(callSuper = true)
public class ChildCode extends AbstractAuditable {

    @Column(name = "base_code_id")
    @NotEmpty(message = "")
    private String baseCodeId;

    @Column(name = "child_code")
    @NotBlank(message = "")
    @Length(max = 20, message = "")
    private String childCode;

    @Column(name = "code_name")
    @NotBlank(message = "")
    @Length(max = 20, message = "")
    private String codeName;

    /**
     * 只能是0和1两个值
     */
    @Column(name = "state")
    @NotNull(message = "")
    @Range(max = 1, message = "")
    private Byte state;

    @Column(name = "description")
    private String description;


}
