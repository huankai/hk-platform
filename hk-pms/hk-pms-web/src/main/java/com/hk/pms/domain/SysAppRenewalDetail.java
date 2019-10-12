package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * App 续期明细
 *
 * @author: kevin
 * @date: 2018-09-20 14:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(value = "sys_app_renewal_detail")
public class SysAppRenewalDetail extends AbstractAuditable {

    /**
     * App
     */
    @Column(value = "app_id")
    private String appId;

    /**
     * 续期类型
     */
    @Column(value = "renewal_type")
    private String renewalType;

    /**
     * 开始时间
     */
    @Column(value = "renewal_start_date")
    private LocalDate renewalStartDate;

    /**
     * 结束时间
     */
    @Column(value = "renewal_end_date")
    private LocalDate renewalEndDate;

    /**
     * 描述
     */
    @Column(value = "description")
    private String description;


}
