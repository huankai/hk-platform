package com.hk.quartz.service;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.quartz.entity.QuartzJobLog;

/**
 * @author kevin
 * @date 2019-8-16 16:45
 */
public interface JobLogService {

    QueryPage<QuartzJobLog> queryForPage(QueryModel<QuartzJobLog> query);
}
