package com.hk.pms.service.impl;


import com.hk.core.data.commons.dao.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysUserThird;
import com.hk.pms.repository.SysUserThirdRepository;
import com.hk.pms.service.SysUserThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018-04-12 17:03
 */
@Service
public class SysUserThirdServiceImpl extends BaseServiceImpl<SysUserThird, String> implements SysUserThirdService {

    private final SysUserThirdRepository sysUserThirdRepository;

    @Autowired
    public SysUserThirdServiceImpl(SysUserThirdRepository sysUserThirdRepository) {
        this.sysUserThirdRepository = sysUserThirdRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysUserThird, String> getBaseDao() {
        return sysUserThirdRepository;
    }
}
