package com.hk.emi.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.emi.domain.BaseCode;

import java.util.Optional;

/**
 * @author huangkai
 * @date 2019-04-04 22:09
 */
public interface BaseCodeRepository extends LongIdJpaRepository<BaseCode> {

    Optional<BaseCode> findByBaseCode(String baseCode);
}
