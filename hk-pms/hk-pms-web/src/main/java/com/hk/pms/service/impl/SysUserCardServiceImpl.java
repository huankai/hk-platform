package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.JpaBaseRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysUserCard;
import com.hk.pms.repository.jpa.SysUserCardRepository;
import com.hk.pms.service.SysUserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:42
 */
@Service
public class SysUserCardServiceImpl extends JpaServiceImpl<SysUserCard, String> implements SysUserCardService {

    private SysUserCardRepository userCardRepository;

    @Autowired
    public SysUserCardServiceImpl(SysUserCardRepository userCardRepository) {
        this.userCardRepository = userCardRepository;
    }

    @Override
    protected JpaBaseRepository<SysUserCard, String> getBaseRepository() {
        return userCardRepository;
    }

    @Override
    public Optional<SysUserCard> findByUserId(String userId) {
        return userCardRepository.findByUserId(userId);
    }
}
