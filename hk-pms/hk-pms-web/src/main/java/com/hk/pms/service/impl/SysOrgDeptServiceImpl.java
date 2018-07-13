package com.hk.pms.service.impl;


import com.hk.core.data.commons.dao.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysOrgDept;
import com.hk.pms.repository.SysOrgDeptRepository;
import com.hk.pms.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysOrgDept, String> getBaseDao() {
        return sysOrgDeptRepository;
    }
}
