package com.hk.pms.service.impl;


import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.exception.ServiceException;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysUser;
import com.hk.pms.repository.jpa.SysUserRepository;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 17:01
 */
@Service
public class SysUserServiceImpl extends JpaServiceImpl<SysUser, Long> implements SysUserService {

    private final SysUserRepository sysUserRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SysUserServiceImpl(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 返回 BaseRepository
     *
     * @return sysUserRepository
     */
    @Override
    protected BaseJpaRepository<SysUser, Long> getBaseRepository() {
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
        if (optionalUser.isPresent()) {
            SysUser sysUser = optionalUser.get();
            if (ByteConstants.NINE.equals(sysUser.getUserStatus())) {
                logger.warn("查询到用户[{}]状态为已删除 :[{}],返回空用户", username, sysUser.getUserStatus());
                return Optional.empty();
            }
        }
        return optionalUser;
    }

    @Override
    public Optional<SysUser> findByAccount(String username) {
        Optional<SysUser> optionalUser = sysUserRepository.findByAccount(username);
        return getValidateUser(optionalUser);
    }

    @Override
    public Optional<SysUser> findByEmail(String email) {
        Optional<SysUser> optionalUser = sysUserRepository.findByEmail(email);
        return getValidateUser(optionalUser);
    }

    @Override
    public Optional<SysUser> findByPhone(String phone) {
        Optional<SysUser> optionalUser = sysUserRepository.findByPhone(phone);
        return getValidateUser(optionalUser);
    }

    @Override
    public Optional<SysUser> findById(Long id) {
        Optional<SysUser> optionalSysUser = super.findById(id);
        return getValidateUser(optionalSysUser);
    }

    @Override
    public Optional<SysUser> findOne(SysUser sysUser) {
        Optional<SysUser> optionalSysUser = super.findOne(sysUser);
        return getValidateUser(optionalSysUser);
    }

    private Optional<SysUser> getValidateUser(Optional<SysUser> optionalUser) {
        if (optionalUser.isPresent() && ByteConstants.NINE.equals(optionalUser.get().getUserStatus())) {
            logger.warn("查询到用户[{}]状态为已删除 :[{}],返回空用户", optionalUser.get().getAccount(), ByteConstants.NINE);
        }
        return optionalUser;
    }

    @Override
    @Transactional
    public void disable(Long userId) {
        updateStatus(userId, ByteConstants.ZERO);
    }

    @Override
    @Transactional
    public void enable(Long userId) {
        updateStatus(userId, ByteConstants.ONE);
    }

    @Override
    @Transactional
    public void resetPassword(Long id, String oldPassword, String newPassword) {
        SysUser user = getOne(id);
        checkOldPassword(user, oldPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        updateById(user);
    }

    private void checkOldPassword(SysUser user, String oldPassword) {
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new ServiceException("原密码可能不正确！");
        }
    }

    @Override
    public void deleteById(Long id) {
        updateStatus(id, ByteConstants.NINE);
    }

    @Override
    public void deleteByIds(Iterable<Long> ids) {
        ids.forEach(id -> updateStatus(id, ByteConstants.NINE));
    }

    @Override
    public void delete(Iterable<SysUser> entities) {
        entities.forEach(item -> updateStatus(item.getId(), ByteConstants.NINE));
    }

    @Override
    public void delete(SysUser entity) {
        updateStatus(entity.getId(), ByteConstants.NINE);
    }

    private void updateStatus(Long userId, Byte userStatus) {
        findById(userId).ifPresent(user -> {
            user.setUserStatus(userStatus);
            updateById(user);
            logger.info("用户[{}]状态已更新,更新后的状态为：{}", userId, userStatus);
        });
    }

    @Override
    public SysUser insert(SysUser sysUser) {
        return insert(sysUser, item -> {
            item.setPassword(passwordEncoder.encode(StringUtils.isEmpty(item.getPassword()) ?
                    item.getAccount() : item.getPassword()));
            if (Objects.isNull(item.getUserStatus())) {
                item.setUserStatus(ByteConstants.TWO);
            }
//            if (Objects.isNull(item.getUserType())) {
//                item.setUserStatus(ByteConstants.NINE);
//            }
            return item;
        });
    }
}
