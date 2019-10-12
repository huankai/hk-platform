package com.hk.quartz.init;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.JsonUtils;
import com.hk.quartz.entity.QuartzJob;
import com.hk.quartz.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author kevin
 * @date 2019-8-16 16:55
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobCommandLineRunner implements CommandLineRunner {

    private final JobService jobService;

    @Override
    public void run(String... args) {
        String testTaskExecutorBeanName = "testTaskExecutor";
        Optional<QuartzJob> testTaskExecutorQuartzJob = jobService.findByBeanName(testTaskExecutorBeanName);
        if (!testTaskExecutorQuartzJob.isPresent()) {
            QuartzJob quartzJob = new QuartzJob();
            quartzJob.setBeanName(testTaskExecutorBeanName);
            quartzJob.setState(ByteConstants.ONE);
            quartzJob.setParams("params");
            quartzJob.setCronExpression("*/5 * * * * ?");
            quartzJob.setRemark("测试");
            QuartzJob saveJob = jobService.saveJob(quartzJob);
            log.info("Init add Job:{}", JsonUtils.serialize(saveJob));
        }
    }
}
