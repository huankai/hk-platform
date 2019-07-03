package com.hk.pms.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysConfig;

import java.util.List;

/**
 * @author kevin
 * @date 2018-09-20 20:06
 */
public interface SysConfigService extends JpaBaseService<SysConfig, Long> {

    List<SysConfig> findByAppId(Long appId);
}
