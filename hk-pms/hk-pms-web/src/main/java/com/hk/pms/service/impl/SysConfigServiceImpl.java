package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.JpaBaseRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysConfig;
import com.hk.pms.repository.jpa.SysConfigRepository;
import com.hk.pms.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-09-20 20:07
 */
@Service
public class SysConfigServiceImpl extends JpaServiceImpl<SysConfig, String> implements SysConfigService {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Override
    protected JpaBaseRepository<SysConfig, String> getBaseRepository() {
        return sysConfigRepository;
    }
}
