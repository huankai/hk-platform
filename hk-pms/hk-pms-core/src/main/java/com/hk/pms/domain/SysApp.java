package com.hk.pms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.core.data.commons.typedef.JsonTypeDef;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import com.hk.pms.enums.AppStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * App Entity
 *
 * @author kevin
 * @date 2018-04-12 11:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_oauth_client_details")
@SuppressWarnings("serial")
public class SysApp extends AbstractSnowflakeAuditable {

    /**
     * 原 secret
     */
    @JsonIgnore
    @Column(name = "original_secret")
    private String originalSecret;

    /**
     * 加密后的 secret
     */
    @JsonIgnore
    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "resource_ids")
    @Type(type = JsonTypeDef.json)
    private Set<String> resourceIds;

    @Column(name = "scope")
    @Type(type = JsonTypeDef.json)
    private Set<String> scope;

    @Column(name = "authorized_grant_types")
    @Type(type = JsonTypeDef.json)
    private Set<String> authorizedGrantTypes;

    @Column(name = "redirect_uri")
    @Type(type = JsonTypeDef.json)
    private Set<String> redirectUri;

    @Column(name = "autoapprove")
    private Boolean autoapprove;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "additional_information")
    @Type(type = JsonTypeDef.json)
    private Map<String, Object> additionalInformation;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "app_code")
    private String appCode;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_status")
    private Boolean appStatus;

    @Column(name = "app_icon")
    private String appIcon;

    /**
     * 应用有效开始时间
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * 应用有效结束时间
     */
    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "description")
    private String description;


    public String getAppStatusColor() {
        return AppStatusEnum.getColor(appStatus);
    }

    public String getAppStatusText() {
        return AppStatusEnum.getText(appStatus);
    }

}
