package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.repository.SysOrgRepository;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 16:52
 */
@Service
public class SysOrgServiceimpl extends BaseServiceImpl<SysOrg, String> implements SysOrgService {

    private final SysOrgRepository sysOrgRepository;

    @Autowired
    public SysOrgServiceimpl(SysOrgRepository sysOrgRepository) {
        this.sysOrgRepository = sysOrgRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysOrg, String> getBaseRepository() {
        return sysOrgRepository;
    }
}
