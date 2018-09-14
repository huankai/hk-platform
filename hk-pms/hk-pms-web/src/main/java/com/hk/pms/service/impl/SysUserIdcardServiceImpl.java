package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysUserIdcard;
import com.hk.pms.repository.SysUserIdcardRepository;
import com.hk.pms.service.SysUserIdcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:42
 */
@Service
public class SysUserIdcardServiceImpl extends BaseServiceImpl<SysUserIdcard, String> implements SysUserIdcardService {

    private SysUserIdcardRepository userIdcardRepository;

    @Autowired
    public SysUserIdcardServiceImpl(SysUserIdcardRepository userIdcardRepository) {
        this.userIdcardRepository = userIdcardRepository;
    }

    @Override
    protected BaseRepository<SysUserIdcard, String> getBaseRepository() {
        return userIdcardRepository;
    }

    @Override
    public Optional<SysUserIdcard> findByUserId(String userId) {
        return userIdcardRepository.findByUserId(userId);
    }
}
