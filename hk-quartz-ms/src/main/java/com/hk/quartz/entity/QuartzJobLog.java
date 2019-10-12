package com.hk.quartz.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeIdPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 定时任务日志
 *
 * @author kevin
 * @date 2019-8-16 15:18
 */
@Data
@Entity
@Table(name = "quartz_job_log")
@EqualsAndHashCode(callSuper = true)
public class QuartzJobLog extends AbstractSnowflakeIdPersistable {

    /**
     * 任务Id
     */
    private Long jobId;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 开始时间
     */
    private LocalDateTime startDate;

    /**
     * 耗时(单位：秒)
     */
    private Long takeTime;

}
