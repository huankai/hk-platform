package com.hk.pms.service.impl;


import com.hk.commons.util.ObjectUtils;
import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.repository.jpa.SysOrgRepository;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kevin
 * @date 2018-04-12 16:52
 */
@Service
@CacheConfig(cacheNames = {"SysOrg"})
public class SysOrgServiceImpl extends EnableJpaCacheServiceImpl<SysOrg, Long> implements SysOrgService {

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
    protected BaseJpaRepository<SysOrg, Long> getBaseRepository() {
        return sysOrgRepository;
    }

    @Override
    public SysOrg insert(SysOrg sysOrg) {
        return insert(sysOrg, item -> {
            item.setParentId(ObjectUtils.defaultIfNull(item.getParentId(), 0L));
            return item;
        });
    }

    @Override
    @Transactional
    public SysOrg updateById(SysOrg org) {
//        SysOrg sysOrg = getOne(org.getId());
//        UserPrincipal principal = getPrincipal();
//        if (!principal.isAdministrator() && ObjectUtils.nullSafeEquals(principal.getUserId(), sysOrg.getResponsibleId())) {
//            throw new ServiceException(getMessage("no.admin.disable.operation"));
//        }
        return super.updateById(org);
    }
}
