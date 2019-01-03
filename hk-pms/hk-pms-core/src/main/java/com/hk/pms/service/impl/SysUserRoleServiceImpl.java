package com.hk.pms.service.impl;


import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysUserRole;
import com.hk.pms.repository.jdbc.SysUserRoleRepository;
import com.hk.pms.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-04-12 17:02
 */
@Service
public class SysUserRoleServiceImpl extends JdbcServiceImpl<SysUserRole,String> implements SysUserRoleService {

    private final SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    public SysUserRoleServiceImpl(SysUserRoleRepository sysUserRoleRepository) {
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected JdbcRepository<SysUserRole, String> getBaseRepository() {
        return sysUserRoleRepository;
    }

    @Override
    public void deleteByUserIdAndRoleId(String userId, String roleId) {
        sysUserRoleRepository.deleteByUserIdAndRoleId(userId,roleId);
    }
}
