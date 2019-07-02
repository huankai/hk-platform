package com.hk.emi.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.emi.domain.ChildCode;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-04 22:11
 */
public interface ChildCodeRepository extends LongIdJpaRepository<ChildCode> {

    List<ChildCode> findByBaseCodeIdOrderByCodeValueAsc(Long baseCodeId);

    String findByBaseCodeIdAndCodeValue(Long baseCodeId, Number value);
}
