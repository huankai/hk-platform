package com.hk.pms.service;


import com.hk.core.cache.service.CacheService;
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

    /**
     * 标记为删除
     *
     * @param id
     */
    default void markDelete(Long id) {
        findById(id).ifPresent(item -> {
            item.setDeleteStatus(true);
            SysAppService proxy = CacheService.currentProxy();
            proxy.updateById(item);
        });
    }

    /**
     * 标记为未删除
     *
     * @param id
     */
    default void markRecovery(Long id) {
        findById(id).ifPresent(item -> {
            item.setDeleteStatus(false);
            SysAppService proxy = CacheService.currentProxy();
            proxy.updateById(item);
        });
    }
}
