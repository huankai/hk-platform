package com.hk.emi.repository.jdbc;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.emi.domain.ChildCode;

/**
 * @author kevin
 * @date 2017年12月1日下午2:20:44
 */
public interface ChildCodeRepository extends StringIdJdbcRepository<ChildCode> {

    @Query(value = "SELECT t.* FROM emi_child_code t WHERE base_code_id = :baseCodeId AND state = 1")
    List<ChildCode> findByBaseCodeIdOrderByCodeValueAsc(@Param("baseCodeId") String baseCodeId);

    @Query(value = "SELECT code_name FROM emi_child_code WHERE base_code_id = :baseCodeId AND code_value = :value AND state = 1")
    String findByBaseCodeIdAndCodeValue(@Param("baseCodeId") String baseCodeId, @Param("value") Number value);
}
