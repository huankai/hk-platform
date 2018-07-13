package com.hk.pms.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysApp;

/**
 * @author: kevin
 * @date 2018-04-12 11:34
 */
public interface SysAppRepository extends StringRepository<SysApp> {

    SysApp findByAppCode(String appCode);
}
