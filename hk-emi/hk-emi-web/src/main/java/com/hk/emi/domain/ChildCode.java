package com.hk.emi.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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
    private String baseCodeId;

    @Column(name = "child_code")
    private String childCode;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "state")
    private Byte state;

    @Column(name = "description")
    private String description;


}
