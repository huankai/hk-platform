package com.hk.pms.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysUserCard;
import com.hk.pms.repository.jpa.SysUserCardRepository;
import com.hk.pms.service.SysUserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-08-31 11:42
 */
@Service
public class SysUserCardServiceImpl extends JpaServiceImpl<SysUserCard, Long> implements SysUserCardService {

    private SysUserCardRepository userCardRepository;

    @Autowired
    public SysUserCardServiceImpl(SysUserCardRepository userCardRepository) {
        this.userCardRepository = userCardRepository;
    }

    @Override
    protected BaseJpaRepository<SysUserCard, Long> getBaseRepository() {
        return userCardRepository;
    }

    @Override
    public Optional<SysUserCard> findByUserId(Long userId) {
        return userCardRepository.findByUserId(userId);
    }
}
