package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysOrgDept;
import com.hk.pms.repository.SysOrgDeptRepository;
import com.hk.pms.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 16:50
 */
@Service
public class SysOrgDeptServiceImpl extends BaseServiceImpl<SysOrgDept, String> implements SysOrgDeptService {

    private final SysOrgDeptRepository sysOrgDeptRepository;

    @Autowired
    public SysOrgDeptServiceImpl(SysOrgDeptRepository sysOrgDeptRepository) {
        this.sysOrgDeptRepository = sysOrgDeptRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("deptName", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysOrgDept, String> getBaseRepository() {
        return sysOrgDeptRepository;
    }
}
