package com.hk.message.web.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_message")
@SuppressWarnings("serial")
public class Message extends AbstractSnowflakeAuditable {

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 消息内容
     */
    @Column(name = "msg_content")
    private String msgContent;

    /**
     * 发送者机构id
     */
    @Column(name = "school_id")
    private String schoolId;

    /**
     * 发送者机构学期Id
     */
    @Column(name = "semester_id")
    private Long semesterId;

    /**
     * 发送者id
     */
    @Column(name = "sender_by")
    private Long senderBy;

    /**
     * 发送者名称
     */
    @Column(name = "sender_name")
    private String senderName;

    /**
     * 发送时间
     */
    @Column(name = "sender_date")
    private LocalDateTime senderDate;

    /**
     * 发送的用户类型
     */
    @Column(name = "sender_to")
    private String senderTo;
}
