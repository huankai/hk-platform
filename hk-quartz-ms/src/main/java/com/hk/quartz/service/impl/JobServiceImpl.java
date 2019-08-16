package com.hk.quartz.service.impl;

import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.ObjectUtils;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.quartz.ScheduleJob;
import com.hk.quartz.entity.QuartzJob;
import com.hk.quartz.repository.jpa.QuartzJobRepository;
import com.hk.quartz.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author kevin
 * @date 2019-8-16 15:18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;

    private final QuartzJobRepository quartzJobRepository;

    @Override
    public QuartzJob findById(Long id) {
        return quartzJobRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<QuartzJob> findByBeanName(String beanName) {
        return quartzJobRepository.findByBeanName(beanName);
    }

    @Override
    @Transactional
    @SneakyThrows
    public QuartzJob saveJob(QuartzJob job) {
        AssertUtils.isTrue(CronExpression.isValidExpression(job.getCronExpression()), "Cron 表达式错误");
        Long id = job.getId();
        job.setCreatedDate(LocalDateTime.now());
        job.setLastModifiedDate(job.getCreatedDate());
        QuartzJob quartzJob = quartzJobRepository.save(job);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(ScheduleJob.JOB_PARAM_KEY, job);
        JobKey jobKey = getJobKey(job.getId(), quartzJob.getJobGroup());
        JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class)
                .withIdentity(jobKey)
                .withDescription(job.getRemark())
                .setJobData(jobDataMap)
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                .cronSchedule(job.getCronExpression())
                .withMisfireHandlingInstructionDoNothing();
        TriggerKey triggerKey = getTriggerKey(quartzJob.getId(), quartzJob.getTriggerGroup());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(scheduleBuilder)
                .build();
        if (null == id) {//添加
            scheduler.scheduleJob(jobDetail, trigger);
        } else { // 更新
            scheduler.rescheduleJob(triggerKey, trigger);
        }
        if (ByteConstants.TWO.equals(job.getState())) {
            scheduler.pauseJob(jobKey);
        }
        return quartzJob;
    }

    private TriggerKey getTriggerKey(Long id, String triggerGroup) {
        return TriggerKey.triggerKey(String.valueOf(id), triggerGroup);
    }

    private JobKey getJobKey(Long id, String jobGroup) {
        return JobKey.jobKey(String.valueOf(id), jobGroup);
    }

    @Override
    public QueryPage<QuartzJob> queryForPage(QueryModel<QuartzJob> query) {
        QuartzJob param = ObjectUtils.defaultIfNull(query.getParam(), new QuartzJob());
        return quartzJobRepository.findByPage(Example.of(param),
                query.getOrders(), query.getStartRowIndex(), query.getPageSize());
    }

    @Override
    public boolean trigger(Long id) {
        try {
            QuartzJob quartzJob = findById(id);
            AssertUtils.notNull(quartzJob, "任务不存在");
            AssertUtils.isTrue(ByteConstants.ONE.equals(quartzJob.getState()), "任务非启动状态，无法执行");
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(ScheduleJob.JOB_PARAM_KEY, quartzJob);
            scheduler.triggerJob(getJobKey(id, quartzJob.getJobGroup()), jobDataMap);
            return true;
        } catch (SchedulerException e) {
            log.warn("scheduler trigger error:{}", e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean pause(Long id) {
        try {
            QuartzJob quartzJob = quartzJobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("任务不存在,任务Id:" + id));
            scheduler.pauseJob(getJobKey(id, quartzJob.getJobGroup()));
            return quartzJobRepository.updateState(id, ByteConstants.TWO) > 0;
        } catch (SchedulerException e) {
            log.warn("scheduler pause error:{}", e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean resume(Long id) {
        try {
            QuartzJob quartzJob = quartzJobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("任务不存在,任务Id:" + id));
            scheduler.resumeJob(getJobKey(id, quartzJob.getJobGroup()));
            return quartzJobRepository.updateState(id, ByteConstants.ONE) > 0;
        } catch (SchedulerException e) {
            log.warn("scheduler resume error:{}", e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        try {
            QuartzJob quartzJob = quartzJobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("任务不存在,任务Id:" + id));
            scheduler.deleteJob(getJobKey(id, quartzJob.getJobGroup()));
            return quartzJobRepository.updateState(id, ByteConstants.ZERO) > 0;
        } catch (SchedulerException e) {
            log.warn("scheduler remove error:{}", e.getMessage());
            return false;
        }
    }
}
