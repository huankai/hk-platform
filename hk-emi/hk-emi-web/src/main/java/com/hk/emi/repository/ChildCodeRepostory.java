package com.hk.emi.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.emi.domain.ChildCode;

import java.util.List;

/**
 * @author: kevin
 * @date 2017年12月1日下午2:20:44
 */
public interface ChildCodeRepostory extends StringRepository<ChildCode> {

    List<ChildCode> findByBaseCodeIdOrderByChildCodeAsc(String baseCodeId);
}
