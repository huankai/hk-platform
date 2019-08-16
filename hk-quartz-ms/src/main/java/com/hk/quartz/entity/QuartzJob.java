package com.hk.quartz.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeIdPersistable;
import com.hk.quartz.enums.QuartzJobStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 定时任务
 *
 * @author kevin
 * @date 2019-8-16 15:18
 */
@Data
@Entity
@Table(name = "quartz_job")
@EqualsAndHashCode(callSuper = true)
public class QuartzJob extends AbstractSnowflakeIdPersistable {

    /**
     * Spring bean 名称
     */
    @Column(name = "bean_name", nullable = false, updatable = false)
    private String beanName;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "trigger_group")
    private String triggerGroup;

    /**
     * 参数
     */
    @Column(name = "params")
    private String params;

    /**
     * cron 表达式
     */
    @Column(name = "cron_expression", nullable = false)
    private String cronExpression;

    /**
     * 状态
     * 0：删除
     * 1: 启用
     * 2：停止
     */
    @Column(name = "state", nullable = false)
    private Byte state;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "remark")
    private String remark;

    public String getStateText() {
        return QuartzJobStatus.getText(state);
    }

}
