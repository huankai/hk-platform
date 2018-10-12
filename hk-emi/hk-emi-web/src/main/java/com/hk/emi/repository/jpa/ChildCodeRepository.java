package com.hk.emi.repository.jpa;


import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.emi.domain.ChildCode;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: kevin
 * @date: 2017年12月1日下午2:20:44
 */
public interface ChildCodeRepository extends StringIdJpaRepository<ChildCode> {

    @Query(value = "SELECT id,base_code_id,child_code,code_name,code_value,state,description,created_by,created_date,last_modified_by,last_modified_date " +
            "FROM sys_child_code WHERE base_code_id = ?1 AND state = 1", nativeQuery = true)
    List<ChildCode> findByBaseCodeIdOrderByCodeValueAsc(String baseCodeId);

    @Query(value = "SELECT code_name FROM sys_child_code WHERE base_code_id = ?1 AND code_value = ?2 AND state = 1", nativeQuery = true)
    String findByBaseCodeIdAndCodeValue(String baseCodeId, byte value);
}
