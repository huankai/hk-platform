package com.hk.pms.service.impl;


import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.data.jpa.repository.JpaBaseRepository;
import com.hk.core.exception.ServiceException;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.repository.jpa.SysOrgRepository;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-04-12 16:52
 */
@Service
public class SysOrgServiceImpl extends JpaServiceImpl<SysOrg, String> implements SysOrgService {

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
    protected JpaBaseRepository<SysOrg, String> getBaseRepository() {
        return sysOrgRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("orgCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("orgName", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public SysOrg updateById(SysOrg org) {
        SysOrg sysOrg = getOne(org.getId());
        UserPrincipal principal = getPrincipal();
        if (!principal.isAdministrator() && StringUtils.notEquals(principal.getUserId(), sysOrg.getResponsibleId())) {
            throw new ServiceException("非管理员不能修改");
        }
        return super.updateById(org);
    }
}
