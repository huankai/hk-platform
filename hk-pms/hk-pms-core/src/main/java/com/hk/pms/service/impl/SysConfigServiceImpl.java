package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysConfig;
import com.hk.pms.repository.jpa.SysConfigRepository;
import com.hk.pms.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-09-20 20:07
 */
@Service
public class SysConfigServiceImpl extends JpaServiceImpl<SysConfig, Long> implements SysConfigService {

    private final SysConfigRepository sysConfigRepository;

    @Autowired
    public SysConfigServiceImpl(SysConfigRepository sysConfigRepository) {
        this.sysConfigRepository = sysConfigRepository;
    }

    @Override
    protected BaseJpaRepository<SysConfig, Long> getBaseRepository() {
        return sysConfigRepository;
    }
}
