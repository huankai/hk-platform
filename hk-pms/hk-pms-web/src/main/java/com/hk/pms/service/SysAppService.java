package com.hk.pms.service;


import com.hk.core.service.BaseService;
import com.hk.pms.domain.SysApp;

/**
 * @author: kevin
 * @date 2018-04-12 11:25
 */
public interface SysAppService extends BaseService<SysApp, String> {

    /**
     * 根据appCode 查询唯一
     *
     * @param appCode appCode
     * @return
     */
    SysApp findByAppCode(String appCode);

    void disable(String id);

    void enable(String id);
}
