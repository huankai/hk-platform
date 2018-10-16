package com.hk.emi.repository.jpa;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.emi.domain.BaseCode;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2017年12月1日下午2:20:48
 */
public interface BaseCodeRepository extends StringIdJdbcRepository<BaseCode> {

    Optional<BaseCode> findByBaseCode(String baseCode);
}
