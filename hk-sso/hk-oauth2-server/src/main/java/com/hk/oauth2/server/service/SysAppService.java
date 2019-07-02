package com.hk.oauth2.server.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.oauth2.server.entity.SysApp;

/**
 * @author kevin
 * @date 2018-08-03 08:56
 */
public interface SysAppService extends JpaBaseService<SysApp, Long> {

    SysApp getByClientId(String clientId);
}
