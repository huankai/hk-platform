package com.hk.pms.service;


import com.hk.core.cache.service.JpaCacheService;
import com.hk.pms.domain.SysApp;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 11:25
 */
public interface SysAppService extends JpaCacheService<SysApp, Long> {

    /**
     * 根据appCode 查询唯一
     *
     * @param appCode appCode
     */
    Optional<SysApp> findByAppCode(String appCode);

    void disable(Long id);

    void enable(Long id);
}
