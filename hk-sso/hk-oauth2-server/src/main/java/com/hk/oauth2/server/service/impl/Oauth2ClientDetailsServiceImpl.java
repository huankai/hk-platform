package com.hk.oauth2.server.service.impl;

import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.repository.jpa.Oauth2ClientDetailsRepository;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2019-7-4 17:47
 */
@Service
@CacheConfig(cacheNames = {"Oauth2ClientDetails"})
public class Oauth2ClientDetailsServiceImpl extends EnableJpaCacheServiceImpl<Oauth2ClientDetails, Long> implements Oauth2ClientDetailsService {

    @Autowired
    private Oauth2ClientDetailsRepository oauth2ClientDetailsRepository;

    @Override
    protected BaseJpaRepository<Oauth2ClientDetails, Long> getBaseRepository() {
        return oauth2ClientDetailsRepository;
    }

}
