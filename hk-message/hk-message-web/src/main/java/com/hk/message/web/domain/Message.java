package com.hk.message.web.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_message")
public class Message extends AbstractAuditable {

    /**
     * 标题
     */
    @Column(value = "title")
    private String title;

    /**
     * 消息内容
     */
    @Column(value = "msg_content")
    private String msgContent;

    /**
     * 发送者机构id
     */
    @Column(value = "school_id")
    private String schoolId;

    /**
     * 发送者机构学期Id
     */
    @Column(value = "semester_id")
    private String semesterId;

    /**
     * 发送者id
     */
    @Column(value = "sender_by")
    private String senderBy;

    /**
     * 发送者名称
     */
    @Column(value = "sender_name")
    private String senderName;

    /**
     * 发送时间
     */
    @Column(value = "sender_date")
    private LocalDateTime senderDate;

    /**
     * 发送的用户类型
     */
    @Column(value = "sender_to")
    private String senderTo;
}
