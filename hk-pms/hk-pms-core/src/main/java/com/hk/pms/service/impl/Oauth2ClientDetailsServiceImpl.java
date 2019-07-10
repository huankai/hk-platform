package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.Oauth2ClientDetails;
import com.hk.pms.repository.jpa.Oauth2ClientDetailsRepository;
import com.hk.pms.service.Oauth2ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-01-03 16:57
 */
@Service
public class Oauth2ClientDetailsServiceImpl extends JpaServiceImpl<Oauth2ClientDetails, Long> implements Oauth2ClientDetailsService {

    @Autowired
    private Oauth2ClientDetailsRepository oauth2ClientDetailsRepository;

    @Override
    protected BaseJpaRepository<Oauth2ClientDetails, Long> getBaseRepository() {
        return oauth2ClientDetailsRepository;
    }
}
