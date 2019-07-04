package com.hk.oauth2.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.typedef.JsonTypeDef;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author kevin
 * @date 2019-7-4 17:45
 */
@Data
@Entity
@Table(name = "pms_oauth_client_details")
@TypeDef(name = JsonTypeDef.json, typeClass = JsonStringType.class)
@EqualsAndHashCode(callSuper = true)
public class Oauth2ClientDetails extends AbstractSnowflakeAuditable implements ClientDetails {

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

    @Column(name = "description")
    private String description;

    @Override
    @Transient
    public String getClientId() {
        return getId() == null ? null : getId().toString();
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isSecretRequired() {
        return StringUtils.isNotEmpty(clientSecret);
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isScoped() {
        return CollectionUtils.isNotEmpty(getScope());
    }

    @Override
    @Transient
    @JsonIgnore
    public Set<String> getRegisteredRedirectUri() {
        return redirectUri;
    }

    @Override
    @Transient
    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(StringUtils.splitByComma(authorities));
    }

    @Override
    @Transient
    @JsonIgnore
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValidity;
    }

    @Override
    @Transient
    @JsonIgnore
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidity;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isAutoApprove(String scope) {
        if (CollectionUtils.isEmpty(autoapprove)) {
            return false;
        }
        for (String auto : autoapprove) {
            if ("true".equals(auto) || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }
}
