package com.hk.sso.server.service.impl;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.exception.ServiceException;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.sso.server.entity.SysUser;
import com.hk.sso.server.repository.jdbc.UserRepository;
import com.hk.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-07-31 12:54
 */
@Service
public class UserServiceImpl extends JdbcServiceImpl<SysUser, String> implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected JdbcRepository<SysUser, String> getBaseRepository() {
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
        if (optionalUser.isPresent()) {
            List<SysUser> users = findAll(optionalUser.get());
            System.out.println(JsonUtils.serialize(users, true));
        }
        return optionalUser;
    }


    @Override
    public void resetPassword(String userId, String newPass) {

        SysUser user = findById(userId).orElseThrow(() -> new ServiceException("用户不存在"));
        user.setPassword(passwordEncoder.encode(newPass));
//        updateByIdSelective(user);
        updateById(user);

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
    public void delete(Iterable<SysUser> entities) {
        throw new UnsupportedOperationException("不支持的操作！");
    }

    @Override
    public void deleteByIds(Iterable<String> strings) {
        throw new UnsupportedOperationException("不支持的操作！");

    }
}
