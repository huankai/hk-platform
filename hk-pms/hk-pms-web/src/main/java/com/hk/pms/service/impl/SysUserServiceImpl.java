package com.hk.pms.service.impl;


import com.hk.commons.util.ByteConstants;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysUser;
import com.hk.pms.repository.SysUserRepository;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-04-12 17:01
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String> implements SysUserService {

    private final SysUserRepository sysUserRepository;

    @Autowired
    public SysUserServiceImpl(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseRepository<SysUser, String> getBaseRepository() {
        return sysUserRepository;
    }

    @Override
    public Optional<SysUser> findByLoginUsername(String username) {
        return sysUserRepository.findByUserName(username);
    }

    @Override
    public void disable(String userId) {
        updateStatus(userId, ByteConstants.ZERO);
    }

    @Override
    public void enable(String userId) {
        updateStatus(userId, ByteConstants.ONE);
    }

    private void updateStatus(String userId, Byte userStatus) {
        findOne(userId).ifPresent(user -> {
            user.setUserStatus(userStatus);
            insertOrUpdate(user);
        });
    }

    @Override
    protected SysUser saveBefore(SysUser entity) {
        if (null == entity.getUserStatus()) {
            entity.setUserStatus(ByteConstants.ONE);
        }
        if (null == entity.getIsProtect()) {
            entity.setIsProtect(Boolean.FALSE);
        }
        return super.saveBefore(entity);
    }

}
