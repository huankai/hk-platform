package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * App 接入申请
 *
 * @author: kevin
 * @date: 2018-09-20 14:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(value = "sys_app_apply")
public class SysAppApply extends AbstractAuditable {

    /**
     * 申请人
     */
    @Column(value = "user_id")
    private String userId;

    /**
     * app编号
     */
    @Column(value = "app_code")
    private String appCode;

    /**
     * app名称
     */
    @Column(value = "app_name")
    private String appName;

    /**
     * 审核状态
     */
    @Column(value = "audit_status")
    private Byte auditStatus;

    /**
     * 法人代表
     */
    @Column(value = "representative")
    private String representative;

    /**
     * 法人手机号
     */
    @Column(value = "representative_phone")
    private String representativePhone;

    /**
     * 法人邮箱号
     */
    @Column(value = "representative_email")
    private String representativeEmail;

    /**
     * 营业执照
     */
    @Column(value = "business_licence")
    private String businessLicence;

    /**
     * 营业执照图片
     */
    @NotEmpty
    @Column(value = "licence_image")
    private String licenceImage;

    /**
     * 描述
     */
    @Column(value = "description")
    private String description;

    /**
     * 申请时间
     */
    @Column(value = "apply_date")
    private LocalDate applyDate;


}
