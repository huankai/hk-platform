package com.hk.pms.domain;

import com.hk.commons.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

/**
 * @author huangkai
 * @date 2019-01-03 16:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthClientDetails implements Persistable<String> {

    @Id
    @Column(value = "client_id")
    private String clientId;

    @Column(value = "client_secret")
    private String clientSecret;

    @Column(value = "resource_ids")
    private String resourceIds;

    @Column(value = "scope")
    private String scope;

    @Column(value = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(value = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @Column(value = "authorities")
    private String authorities;

    @Column(value = "access_token_validity")
    private String accessTokenValidity;

    @Column(value = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(value = "additional_information")
    private Integer additionalInformation;

    @Column(value = "autoapprove")
    private String autoapprove;

    @Override
    public String getId() {
        return clientId;
    }

    @Override
    public boolean isNew() {
        return StringUtils.isEmpty(clientId);
    }
}
