package com.hk.oauth2.server.service.impl;

import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.repository.jpa.Oauth2ClientDetailsRepository;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2019-7-4 17:47
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"Oauth2ClientDetails"})
public class Oauth2ClientDetailsServiceImpl extends EnableJpaCacheServiceImpl<Oauth2ClientDetails, Long> implements Oauth2ClientDetailsService {

    private final Oauth2ClientDetailsRepository oauth2ClientDetailsRepository;

    @Override
    protected BaseJpaRepository<Oauth2ClientDetails, Long> getBaseRepository() {
        return oauth2ClientDetailsRepository;
    }

}
