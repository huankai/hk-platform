package com.hk.message.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;


/**
 * 消息-- 附件 --- 用户
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class MessageAttachmentUserDTO extends MessageAttachmentDTO {

    private Set<Long> userIds;
}
