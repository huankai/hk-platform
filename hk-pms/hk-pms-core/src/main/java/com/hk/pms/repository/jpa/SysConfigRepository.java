package com.hk.pms.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysConfig;

import java.util.List;

/**
 * @author kevin
 * @date 2018-09-20 15:20
 */
public interface SysConfigRepository extends LongIdJpaRepository<SysConfig> {

    List<SysConfig> findByAppId(Long appId);
}
