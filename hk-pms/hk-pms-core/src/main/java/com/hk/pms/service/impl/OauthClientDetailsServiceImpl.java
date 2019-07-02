package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.OauthClientDetails;
import com.hk.pms.repository.jpa.OauthClientDetailsRepository;
import com.hk.pms.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-01-03 16:57
 */
@Service
public class OauthClientDetailsServiceImpl extends JpaServiceImpl<OauthClientDetails, String> implements OauthClientDetailsService {

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Override
    protected BaseJpaRepository<OauthClientDetails, String> getBaseRepository() {
        return oauthClientDetailsRepository;
    }
}
