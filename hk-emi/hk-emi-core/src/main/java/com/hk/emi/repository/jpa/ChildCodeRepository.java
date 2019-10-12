package com.hk.emi.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.emi.domain.ChildCode;

import java.util.List;
import java.util.Optional;

/**
 * @author huangkai
 * @date 2019-04-04 22:11
 */
public interface ChildCodeRepository extends LongIdJpaRepository<ChildCode> {

    List<ChildCode> findByBaseCodeIdOrderByCodeValueAsc(Long baseCodeId);

    Optional<ChildCode> findByBaseCodeIdAndCodeValue(Long baseCodeId, Number value);

    long countByBaseCodeId(Long baseCodeId);
}
