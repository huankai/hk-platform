package com.hk.quartz.repository.jpa;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.quartz.entity.QuartzJobLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kevin
 * @date 2019-8-16 16:19
 */
public interface QuartzJobLogRepository extends BaseJpaRepository<QuartzJobLog, Long> {

}
