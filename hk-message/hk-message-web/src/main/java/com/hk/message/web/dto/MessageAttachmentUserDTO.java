package com.hk.message.web.dto;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 消息-- 附件 --- 用户
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class MessageAttachmentUserDTO extends MessageAttachmentDTO {

    private Set<String> userIds;
}
