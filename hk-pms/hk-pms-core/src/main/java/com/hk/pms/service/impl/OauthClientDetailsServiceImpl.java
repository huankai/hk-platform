package com.hk.pms.service.impl;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.OauthClientDetails;
import com.hk.pms.repository.jdbc.OauthClientDetailsRepository;
import com.hk.pms.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-01-03 16:57
 */
@Service
public class OauthClientDetailsServiceImpl extends JdbcServiceImpl<OauthClientDetails, String> implements OauthClientDetailsService {

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Override
    protected JdbcRepository<OauthClientDetails, String> getBaseRepository() {
        return oauthClientDetailsRepository;
    }
}
