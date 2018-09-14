package com.hk.emi.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.emi.domain.BaseCode;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2017年12月1日下午2:20:48
 */
public interface BaseCodeRepository extends StringRepository<BaseCode> {

    Optional<BaseCode> findByBaseCode(String baseCode);
}
