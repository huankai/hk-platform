package com.hk.quartz.service.impl;

import com.hk.core.jdbc.query.ConditionQueryModel;
import com.hk.core.page.QueryPage;
import com.hk.quartz.entity.QuartzJobLog;
import com.hk.quartz.repository.jpa.QuartzJobLogRepository;
import com.hk.quartz.service.JobLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2019-8-16 16:46
 */
@Service
@RequiredArgsConstructor
public class JobLogServiceImpl implements JobLogService {

    private final QuartzJobLogRepository quartzJobLogRepository;

    @Override
    public QueryPage<QuartzJobLog> queryForPage(ConditionQueryModel query) {
        return quartzJobLogRepository.queryForPage(query);
    }
}
