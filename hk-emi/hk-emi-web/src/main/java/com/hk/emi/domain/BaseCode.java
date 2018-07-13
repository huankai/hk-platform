package com.hk.emi.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

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
    private String baseCode;

    /**
     *
     */
    @Column(name = "code_name")
    private String codeName;

    /**
     *
     */
    @Column(name = "description")
    private String description;

}
