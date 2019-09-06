package com.hk.pms.service.impl;


import com.hk.commons.util.Contants;
import com.hk.commons.util.ObjectUtils;
import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.platform.commons.tree.AntDesignTreeNode;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.repository.jpa.SysOrgRepository;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("orgCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("orgName", ExampleMatcher.GenericPropertyMatchers.contains());
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
            item.setParentId(ObjectUtils.defaultIfNull(item.getParentId(), Contants.DEFAULT_VALUE_LONG));
            return item;
        });
    }

    @Override
    public SysOrg updateByIdSelective(SysOrg sysOrg) {
        sysOrg.setParentId(ObjectUtils.defaultIfNull(sysOrg.getParentId(), Contants.DEFAULT_VALUE_LONG));
        return super.updateByIdSelective(sysOrg);
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

    @Override
    public List<AntDesignTreeNode> findRootList(Long currentOrgId) {
        return sysOrgRepository.findRootList(currentOrgId);
    }

    @Override
    public List<AntDesignTreeNode> findChildList(Long parentId, Long currentId) {
        return sysOrgRepository.findChildList(parentId, currentId);
    }
}
