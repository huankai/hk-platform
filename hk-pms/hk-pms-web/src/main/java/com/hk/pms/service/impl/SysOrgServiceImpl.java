package com.hk.pms.service.impl;


import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.cache.service.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.exception.ServiceException;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.repository.jdbc.SysOrgRepository;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-04-12 16:52
 */
@Service
@CacheConfig(cacheNames = {"SysOrg"})
public class SysOrgServiceImpl extends EnableJdbcCacheServiceImpl<SysOrg, String> implements SysOrgService {

    private final SysOrgRepository sysOrgRepository;

    @Autowired
    public SysOrgServiceImpl(SysOrgRepository sysOrgRepository) {
        this.sysOrgRepository = sysOrgRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected JdbcRepository<SysOrg, String> getBaseRepository() {
        return sysOrgRepository;
    }

    @Override
    public SysOrg updateById(SysOrg org) {
        SysOrg sysOrg = getById(org.getId());
        UserPrincipal principal = getPrincipal();
        if (!principal.isAdministrator() && StringUtils.notEquals(principal.getUserId(), sysOrg.getResponsibleId())) {
            throw new ServiceException(getMessage("no.admin.disable.operation"));
        }
        return super.updateById(org);
    }
}
