package com.hk.quartz;

import com.hk.commons.util.SpringContextHolder;
import com.hk.quartz.entity.QuartzJob;
import com.hk.quartz.entity.QuartzJobLog;
import com.hk.quartz.repository.jpa.QuartzJobLogRepository;
import com.hk.quartz.task.TaskExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2019-8-16 14:53
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleJob extends QuartzJobBean {

    public static final String JOB_PARAM_KEY = ScheduleJob.class.getSimpleName() + ".JOB_PARAM_KEY";

    private final QuartzJobLogRepository quartzJobLogRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        final QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(JOB_PARAM_KEY);
        boolean containsBean = SpringContextHolder.containsBean(quartzJob.getBeanName());
        if (containsBean) {
            Object bean = SpringContextHolder.getBean(quartzJob.getBeanName());
            if (bean instanceof TaskExecutor) {
                QuartzJobLog quartzJobLog = new QuartzJobLog();
                quartzJobLog.setJobId(quartzJob.getId());
                quartzJobLog.setStartDate(LocalDateTime.now());

                long startTime = System.currentTimeMillis();
                log.info("任务准备执行，任务ID：{}", quartzJob.getId());
                try {
                    ((TaskExecutor) bean).execute(quartzJob.getParams());
                    quartzJobLog.setIsSuccess(true);
                } catch (Exception e) {
                    quartzJobLog.setIsSuccess(false);
                    quartzJobLog.setMessage(e.getMessage());
                    log.error("任务执行失败，任务ID：{},异常信息:{}", quartzJob.getId(), e.getMessage());
                    throw e;
                } finally {
                    long times = (System.currentTimeMillis() - startTime) / 1000;
                    log.info("任务执行完毕，任务ID：{},总耗时:{} 秒", quartzJob.getId(), times);
                    quartzJobLog.setTakeTime(times);
                    quartzJobLogRepository.save(quartzJobLog);
                }
            } else {
                log.error("任务 Bean 类型不能识别:{}", bean.getClass().getName());
            }
        } else {
            log.error("不能识别 Bean, 名称为 :{}", quartzJob.getBeanName());
        }
    }
}
