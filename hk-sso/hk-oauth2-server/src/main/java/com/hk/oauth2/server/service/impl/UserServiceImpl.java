package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.SpringContextHolder;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.repository.jpa.UserRepository;
import com.hk.oauth2.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-07-31 12:54
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends JpaServiceImpl<SysUser, Long> implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected BaseJpaRepository<SysUser, Long> getBaseRepository() {
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
    @Transactional
    public void resetPassword(Long userId, String newPass) {
        SysUser user = getOne(userId);
        user.setPassword(passwordEncoder.encode(newPass));
//        updateByIdSelective(user);
        updateById(user);

    }

    @Override
    public Optional<SysUser> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public SysUser registerUser(SysUser user) {
        return super.insert(user, sysUser -> {
//            if (null == sysUser.getIsProtect()) {
//                sysUser.setIsProtect(false);
//            }
            return sysUser;
        });
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException(SpringContextHolder.getMessage("unSupportOperation.message"));
    }

    @Override
    public void delete(Iterable<SysUser> entities) {
        throw new UnsupportedOperationException(SpringContextHolder.getMessage("unSupportOperation.message"));
    }

    @Override
    public void deleteByIds(Iterable<Long> strings) {
        throw new UnsupportedOperationException(SpringContextHolder.getMessage("unSupportOperation.message"));

    }
}
