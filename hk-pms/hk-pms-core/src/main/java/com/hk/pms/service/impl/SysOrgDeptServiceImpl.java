package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysOrgDept;
import com.hk.pms.repository.jpa.SysOrgDeptRepository;
import com.hk.pms.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-04-12 16:50
 */
@Service
public class SysOrgDeptServiceImpl extends JpaServiceImpl<SysOrgDept, Long> implements SysOrgDeptService {

    private final SysOrgDeptRepository sysOrgDeptRepository;

    @Autowired
    public SysOrgDeptServiceImpl(SysOrgDeptRepository sysOrgDeptRepository) {
        this.sysOrgDeptRepository = sysOrgDeptRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseJpaRepository<SysOrgDept, Long> getBaseRepository() {
        return sysOrgDeptRepository;
    }
}
