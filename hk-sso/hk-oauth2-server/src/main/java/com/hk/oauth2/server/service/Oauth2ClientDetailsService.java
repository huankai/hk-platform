package com.hk.oauth2.server.service;

import com.hk.core.cache.service.JpaCacheService;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;

/**
 * @author kevin
 * @date 2019-7-4 17:46
 */
public interface Oauth2ClientDetailsService extends JpaCacheService<Oauth2ClientDetails, Long> {

}
