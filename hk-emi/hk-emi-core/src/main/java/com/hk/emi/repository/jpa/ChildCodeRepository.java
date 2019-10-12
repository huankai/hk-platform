package com.hk.emi.repository.jpa;

import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.emi.domain.ChildCode;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-04 22:11
 */
public interface ChildCodeRepository extends StringIdJpaRepository<ChildCode> {

    List<ChildCode> findByBaseCodeIdOrderByCodeValueAsc(String baseCodeId);

    String findByBaseCodeIdAndCodeValue(String baseCodeId, Number value);
}
