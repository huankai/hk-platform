package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.repository.jpa.custom.CustomSysOrgRepository;

/**
 * @author kevin
 * @date 2018-04-12 16:38
 */
public interface SysOrgRepository extends LongIdJpaRepository<SysOrg>, CustomSysOrgRepository {
}
