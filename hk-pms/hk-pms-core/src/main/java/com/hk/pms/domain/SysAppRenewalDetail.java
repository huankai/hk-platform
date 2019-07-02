package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * App 续期明细
 *
 * @author kevin
 * @date 2018-09-20 14:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_app_renewal_detail")
public class SysAppRenewalDetail extends AbstractSnowflakeAuditable {

    /**
     * App
     */
    @Column(name = "app_id")
    private Long appId;

    /**
     * 续期类型
     */
    @Column(name = "renewal_type")
    private String renewalType;

    /**
     * 开始时间
     */
    @Column(name = "renewal_start_date")
    private LocalDate renewalStartDate;

    /**
     * 结束时间
     */
    @Column(name = "renewal_end_date")
    private LocalDate renewalEndDate;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;


}
