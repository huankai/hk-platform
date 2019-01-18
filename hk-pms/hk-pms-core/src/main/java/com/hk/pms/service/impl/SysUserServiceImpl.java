package com.hk.pms.service.impl;


import com.hk.commons.util.ByteConstants;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.exception.ServiceException;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysUser;
import com.hk.pms.repository.jdbc.SysUserRepository;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 17:01
 */
@Service
public class SysUserServiceImpl extends JdbcServiceImpl<SysUser, String> implements SysUserService {

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
    protected JdbcRepository<SysUser, String> getBaseRepository() {
        return sysUserRepository;
    }

//    @Override
//    protected ExampleMatcher ofExampleMatcher() {
//        return super.ofExampleMatcher()
//                .withIgnorePaths("password")
//                .withMatcher("orgId", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("deptId", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("userType", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("isProtect", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("provinceId", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("cityId", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("userStatus", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("account", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("realName", ExampleMatcher.GenericPropertyMatchers.contains());
//    }

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

//    @Override
//    public SysUser getOne(String id) {
//        SysUser user = super.findById(id).orElseThrow(() -> new ServiceException("记录不存在"));
//        if (ByteConstants.NINE.equals(user.getUserStatus())) {
//            throw new ServiceException("无效用户！");
//        }
//        return user;
//    }

    @Override
    public Optional<SysUser> findById(String id) {
        Optional<SysUser> optionalSysUser = super.findById(id);
        return getValidateUser(optionalSysUser);
    }

    @Override
    public Optional<SysUser> findOne(SysUser sysUser) {
        Optional<SysUser> optionalSysUser = super.findOne(sysUser);
        return getValidateUser(optionalSysUser);
    }

    private Optional<SysUser> getValidateUser(Optional<SysUser> optionalUser) {
        return optionalUser.isPresent() && ByteConstants.NINE.equals(optionalUser.get().getUserStatus()) ?
                Optional.empty() : optionalUser;
    }

    @Override
    public void disable(String userId) {
        updateStatus(userId, ByteConstants.ZERO);
    }

    @Override
    public void enable(String userId) {
        updateStatus(userId, ByteConstants.ONE);
    }

    @Override
    public void resetPassword(String id, String oldPassword, String newPassword) {
        SysUser user = getById(id);
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
    public void deleteById(String id) {
        updateStatus(id, ByteConstants.NINE);
    }

    @Override
    public void deleteByIds(Iterable<String> ids) {
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

    private void updateStatus(String userId, Byte userStatus) {
        findById(userId).ifPresent(user -> {
            user.setUserStatus(userStatus);
            insertOrUpdate(user);
            logger.info("用户[{}]状态已更新,更新后的状态为：{}", userId, userStatus);
        });
    }

}