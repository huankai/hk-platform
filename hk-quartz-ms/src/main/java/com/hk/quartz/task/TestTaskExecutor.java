package com.hk.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2019-8-16 16:58
 */
@Slf4j
@Component
public class TestTaskExecutor implements TaskExecutor {

    @Override
    public void execute(String param) {
        log.info("param ----->: {}", param);
    }
}
