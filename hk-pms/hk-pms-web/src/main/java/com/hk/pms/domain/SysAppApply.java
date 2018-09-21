package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * App 接入申请
 *
 * @author: kevin
 * @date: 2018-09-20 14:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_app_apply")
public class SysAppApply extends AbstractAuditable {

    /**
     * 申请人
     */
    @Column(name = "user_id", updatable = false)
    private String userId;

    /**
     * app编号
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * app名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 审核状态
     */
    @Column(name = "audit_status")
    private Byte auditStatus;

    /**
     * 法人代表
     */
    @Column(name = "representative")
    private String representative;

    /**
     * 法人手机号
     */
    @Column(name = "representative_phone")
    private String representativePhone;

    /**
     * 法人邮箱号
     */
    @Column(name = "representative_email")
    private String representativeEmail;

    /**
     * 营业执照
     */
    @Column(name = "business_licence")
    private String businessLicence;

    /**
     * 营业执照图片
     */
    @NotEmpty
    @Column(name = "licence_image")
    private String licenceImage;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 申请时间
     */
    @Column(name = "apply_date")
    private LocalDate applyDate;


}
