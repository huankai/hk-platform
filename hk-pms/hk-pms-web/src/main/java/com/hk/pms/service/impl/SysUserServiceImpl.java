package com.hk.pms.service.impl;


import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.exception.ServiceException;
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
        Optional<SysUser> optionalUser = sysUserRepository.findByAccount(username);
        if (!optionalUser.isPresent()) {
            optionalUser = sysUserRepository.findByPhone(username);
            if (!optionalUser.isPresent()) {
                optionalUser = sysUserRepository.findByEmail(username);
            }
        }
        return optionalUser;
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
        AssertUtils.notNull(entity.getUserType(), "用户类型不能为空！");
        if (entity.getIsProtect() == null) {
            entity.setIsProtect(Boolean.FALSE);
        }
        Optional<SysUser> optionalUser = sysUserRepository.findByAccount(entity.getAccount());
        if (optionalUser.isPresent()) {
            if (ByteConstants.ONE.equals(entity.getUserStatus())) {
                throw new ServiceException("用户账号已注册:" + entity.getAccount());
            } else {
                SysUser sysUser = optionalUser.get();
                entity.setId(sysUser.getId());
                entity.setUserStatus(ByteConstants.ONE);
                return entity;
            }

        }
        if (StringUtils.isNotEmpty(entity.getPhone())) {
            optionalUser = sysUserRepository.findByPhone(entity.getPhone());
            if (optionalUser.isPresent()) {
                if (ByteConstants.ONE.equals(entity.getUserStatus())) {
                    throw new ServiceException("手机号已注册:" + entity.getPhone());
                } else {
                    SysUser sysUser = optionalUser.get();
                    entity.setId(sysUser.getId());
                    entity.setUserStatus(ByteConstants.ONE);
                    return entity;
                }
            }
        }
        if (StringUtils.isNotEmpty(entity.getEmail())) {
            optionalUser = sysUserRepository.findByEmail(entity.getEmail());
            if (optionalUser.isPresent()) {
                if (ByteConstants.ONE.equals(entity.getUserStatus())) {
                    throw new ServiceException("邮箱号已注册:" + entity.getEmail());
                } else {
                    SysUser sysUser = optionalUser.get();
                    entity.setId(sysUser.getId());
                    entity.setUserStatus(ByteConstants.ONE);
                    return entity;
                }
            }
        }
        return entity;
    }

}
