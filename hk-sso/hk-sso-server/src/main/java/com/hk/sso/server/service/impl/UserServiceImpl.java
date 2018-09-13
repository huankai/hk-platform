package com.hk.sso.server.service.impl;

import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.sso.server.entity.SysUser;
import com.hk.sso.server.repository.UserRepository;
import com.hk.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-07-31 12:54
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser, String> implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected BaseRepository<SysUser, String> getBaseRepository() {
        return userRepository;
    }

    @Override
    public Optional<SysUser> findByLoginName(String loginName) {
        Optional<SysUser> optionalUser = userRepository.findByAccount(loginName);
        if (!optionalUser.isPresent()) {
            optionalUser = userRepository.findByPhone(loginName);
            if (!optionalUser.isPresent()) {
                optionalUser = userRepository.findByEmail(loginName);
            }
        }
        return optionalUser;
    }


    @Override
    public void resetPassword(String userId, String newPass) {
        SysUser user = new SysUser();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(newPass));
        updateByIdSelective(user);
    }

    @Override
    public SysUser updateById(SysUser sysUser) {
        throw new UnsupportedOperationException("不支持的操作！");
    }


    @Override
    public SysUser insert(SysUser sysUser) {
        throw new UnsupportedOperationException("不支持的操作！");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("不支持的操作！");
    }

    @Override
    public void delete(Collection<SysUser> entities) {
        throw new UnsupportedOperationException("不支持的操作！");
    }

    @Override
    public void deleteByIds(Collection<String> strings) {
        throw new UnsupportedOperationException("不支持的操作！");

    }
}
