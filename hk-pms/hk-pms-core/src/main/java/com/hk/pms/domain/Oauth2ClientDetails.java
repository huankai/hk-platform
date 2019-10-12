package com.hk.pms.domain;

import com.hk.core.data.commons.typedef.JsonTypeDef;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * @author kevin
 * @date 2019-7-4 17:45
 */
@Data
@Entity
@Table(name = "sys_oauth_client_details")
@TypeDef(name = JsonTypeDef.json, typeClass = JsonStringType.class)
@EqualsAndHashCode(callSuper = true)
public class Oauth2ClientDetails extends AbstractSnowflakeAuditable {

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

    /**
     * 首页 url
     */
    @NotEmpty
    @Column(name = "main_url")
    private String mainUrl;

    @Column(name = "redirect_uri")
    @Type(type = JsonTypeDef.json)
    private Set<String> redirectUri;

    @Column(name = "autoapprove")
    @Type(type = JsonTypeDef.json)
    private Set<String> autoapprove;

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
    private Byte appStatus;

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

}
