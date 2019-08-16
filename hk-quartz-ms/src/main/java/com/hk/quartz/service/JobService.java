package com.hk.quartz.service;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.quartz.entity.QuartzJob;

import java.util.Optional;

/**
 * @author kevin
 * @date 2019-8-16 15:17
 */
public interface JobService {

    QuartzJob findById(Long id);

    Optional<QuartzJob> findByBeanName(String beanName);

    /**
     * 保存
     *
     * @param job job
     */
    QuartzJob saveJob(QuartzJob job);

    /**
     * 分页查询
     *
     * @param query query
     * @return
     */
    QueryPage<QuartzJob> queryForPage(QueryModel<QuartzJob> query);

    /**
     * 触发任务
     *
     * @param id id
     * @return
     */
    boolean trigger(Long id);

    /**
     * 停止任务
     *
     * @param id
     * @return
     */
    boolean pause(Long id);

    /**
     * 恢复任务
     *
     * @param id
     * @return
     */
    boolean resume(Long id);

    /**
     * 移除任务
     *
     * @param id
     * @return
     */
    boolean remove(Long id);
}
