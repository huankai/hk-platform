package com.hk.pms.service.impl;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysConfig;
import com.hk.pms.repository.jdbc.SysConfigRepository;
import com.hk.pms.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-09-20 20:07
 */
@Service
public class SysConfigServiceImpl extends JdbcServiceImpl<SysConfig, String> implements SysConfigService {

    private final SysConfigRepository sysConfigRepository;

    @Autowired
    public SysConfigServiceImpl(SysConfigRepository sysConfigRepository) {
        this.sysConfigRepository = sysConfigRepository;
    }

    @Override
    protected JdbcRepository<SysConfig, String> getBaseRepository() {
        return sysConfigRepository;
    }
}
